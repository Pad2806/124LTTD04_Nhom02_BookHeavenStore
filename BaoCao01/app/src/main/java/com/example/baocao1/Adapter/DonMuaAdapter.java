package com.example.baocao1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.DonHang;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.R;
import com.example.baocao1.View.LoginActivity;
import com.example.baocao1.View.OrderBuyUserActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonMuaAdapter extends RecyclerView.Adapter<DonMuaAdapter.DonDatViewHolder> {
    private List<DonHang> donHangList;
    private static OnItemClickListener onItemClickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClickDonMua(View view, int position, long id);
    }

        public DonMuaAdapter(List<DonHang> donHangList, OnItemClickListener onItemClickListener, Context context) {
        this.donHangList = donHangList;
        this.onItemClickListener=onItemClickListener;
        this.context=context;
    }
//    public DonMuaAdapter(List<DonHang> donHangList) {
//        this.donHangList = donHangList;
//    }

    @NonNull
    @Override
    public DonDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donmua, parent, false);
        return new DonDatViewHolder(view, donHangList);
    }

    @Override
    public void onBindViewHolder(@NonNull DonDatViewHolder holder, int position) {
        DonHang donhang = donHangList.get(position);
        // Load image using Glide
        String imageUrl = donhang.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.hinhanh);
        holder.tensach.setText(donhang.getTenSach());
        holder.dongia.setText(formatCurrency(Long.parseLong(donhang.getDonGiaBan())));
        holder.soluong.setText("Số lượng: "+donhang.getSoLuong());
        holder.giodat.setText(donhang.getGioDatHang());
        holder.ngaydat.setText(convertDateFormat(donhang.getNgayDatHang()));
        // Kiểm tra thời gian đặt hàng để ẩn/hiện btnhuy
        String ngayDat = donhang.getNgayDatHang(); // Ví dụ: "2024-12-02"
        String gioDat = donhang.getGioDatHang();  // Ví dụ: "08:00:00"
        String fullDateTime = ngayDat + " " + gioDat; // Kết hợp ngày và giờ
        // Kiểm tra trạng thái "Đã hủy"
        if (donhang.getTrangThai().equals("Đã hủy")) {
            holder.btnhuy.setVisibility(View.GONE);
            holder.btndahuy.setVisibility(View.VISIBLE);
        }
        else if (donhang.getTrangThai().equals("Đang giao")) {
            holder.btnhuy.setVisibility(View.GONE);
            holder.btndahuy.setVisibility(View.GONE);
        }
        else {
            // Nếu chưa hủy, kiểm tra thời gian
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                Date dateOrder = sdf.parse(fullDateTime);
                long currentTime = System.currentTimeMillis(); // Lấy thời gian hiện tại
                long orderTime = dateOrder != null ? dateOrder.getTime() : 0;

                // Nếu vượt quá 12 giờ, ẩn nút hủy
                if ((currentTime - orderTime) > 12 * 60 * 60 * 1000) {
                    holder.btnhuy.setVisibility(View.GONE);
                    holder.btndahuy.setVisibility(View.GONE);
                } else {
                    holder.btnhuy.setVisibility(View.VISIBLE);
                    holder.btndahuy.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                holder.btnhuy.setVisibility(View.GONE); // Ẩn nếu có lỗi phân tích thời gian
            }
        }
    }
    private String convertDateFormat(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return "01-01-2000";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                SimpleDateFormat outputFormat = new SimpleDateFormat(" dd-MM-yyyy", Locale.getDefault());
                return outputFormat.format(date);
            } else {
                return "01-01-2001";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "01-01-2002";
        }
    }

    public static String formatCurrency(long number) {
        // Tạo đối tượng DecimalFormatSymbols để tùy chỉnh ký tự phân cách
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.'); // Thiết lập dấu phân cách nhóm là dấu chấm
        // Định dạng tiền tệ với dấu chấm phân tách các phần nghìn
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        // Trả về chuỗi đã định dạng
        return formatter.format(number) + "đ";
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class DonDatViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, dongia, soluong,giodat,ngaydat,btnhuy,btndahuy;
        ImageView hinhanh;
        private List<DonHang> donHangList;

        public DonDatViewHolder(View itemView, List<DonHang> donHangList) {
            super(itemView);
            this.donHangList = donHangList;
            tensach = itemView.findViewById(R.id.tensachDM);
            dongia = itemView.findViewById(R.id.dongiaDM);
            soluong = itemView.findViewById(R.id.soluongDM);
            hinhanh = itemView.findViewById(R.id.hinhanhDM);
            giodat = itemView.findViewById(R.id.giodat);
            ngaydat = itemView.findViewById(R.id.ngaydat);
            btnhuy = itemView.findViewById(R.id.btnhuy);
            btndahuy = itemView.findViewById(R.id.btndahuy);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickDonMua(v, getAdapterPosition(), getItemId());
                    }
                }
            });
            btnhuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(v);
                }
            });
        }
        private void showDialog(View view) {
            // Lấy đơn hàng tại vị trí cụ thể
            int position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return; // Kiểm tra nếu vị trí không hợp lệ
            DonHang currentDonHang = donHangList.get(position);
            Dialog dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.layout_dialog_cancelorder);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.dialog_ordersuccess_bg));
            dialog.setCancelable(false);

            Dialog dialog1 = new Dialog(view.getContext());
            dialog1.setContentView(R.layout.layout_dialog_cancelordersuccessed);
            Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog1.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.dialog_ordersuccess_bg));
            dialog1.setCancelable(false);

            TextView btnCancel = dialog.findViewById(R.id.btnCancel);
            TextView btnAgree = dialog.findViewById(R.id.buyAgree);

            btnCancel.setOnClickListener(v -> dialog.dismiss());

            btnAgree.setOnClickListener(v -> {
                btnhuy.setVisibility(View.GONE);
                btndahuy.setVisibility(View.VISIBLE);
                dialog.dismiss(); // Đóng dialog đầu tiên
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        huyDonHang(LoginActivity.MaKhachHang,currentDonHang.getMaDonHang());
                        dialog1.dismiss();
                    }
                }, 1000);// Hiển thị dialog thứ hai
            });

            dialog.show();
        }
        private void huyDonHang(String makh,String madh ) {
            ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
            Call<APICapNhat> call = apiService.huyDonHang(makh,madh);
            call.enqueue(new Callback<APICapNhat>() {
                @Override
                public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        APICapNhat apiResponse = response.body();
                        Log.d("Hủy thành công", madh+apiResponse.getMessage());
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // Cập nhật trạng thái trong danh sách hiển thị
                        for (DonHang donHang : donHangList) {
                            if (donHang.getMaDonHang().equals(madh)) {
                                donHang.setTrangThai("Đã hủy");
                            }
                        }
                        notifyDataSetChanged(); // Refresh giao diện
                    } else {
                        Log.d("Hủy thất bại", "Error: " +madh+ response.message());
//                    Toast.makeText(getApplicationContext(), "onResponse"+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<APICapNhat> call, Throwable t) {
                    Log.d("API", "Lỗi kết nối"+madh+t.getMessage());
//                Toast.makeText(getApplicationContext(), "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}