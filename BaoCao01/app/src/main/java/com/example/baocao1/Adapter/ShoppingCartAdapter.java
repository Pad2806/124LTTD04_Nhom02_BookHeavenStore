package com.example.baocao1.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.R;
import com.example.baocao1.View.LoginActivity;
import com.example.baocao1.View.UpdateUserInfoActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {
    private static List<Sach_GioHang> sachList;
    private static ArrayList<String> selectedBooks;;
    private static OnItemClickListener onItemClickListener;
    public static long tongtien=0;
    private OnTotalPriceChangeListener totalPriceChangeListener;
    public interface OnTotalPriceChangeListener {
        void onTotalPriceChange(long totalPrices);
    }
    public void setOnTotalPriceChangeListener(OnTotalPriceChangeListener listener) {
        this.totalPriceChangeListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);
    }

    public ShoppingCartAdapter(List<Sach_GioHang> sachList, OnItemClickListener onItemClickListener) {
        this.sachList = sachList;
        this.onItemClickListener=onItemClickListener;
        if (selectedBooks == null) {
            selectedBooks = new ArrayList<>();
        }
    }
//    public ShoppingCartAdapter(List<Sach_GioHang> sachList) {
//        this.sachList = sachList;
//    }

    @NonNull
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new ShoppingCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartViewHolder holder, int position) {
        Sach_GioHang sach = sachList.get(position);
        holder.setIsRecyclable(false);
        // Load image using Glide
        String imageUrl = sach.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.hinhanh);
        holder.tensach.setText(sach.getTenSach());
        holder.soluong.setText(sach.getSoLuong());
        holder.dongia.setText(formatCurrency(Long.parseLong(sach.getDonGiaBan())));
        // Xử lý nút tăng số lượng
        holder.plus.setOnClickListener(v -> {
            int SLHT = Integer.parseInt(sach.getSoLuong());
            // Nếu sản phẩm đang được chọn, cập nhật tổng tiền
            if (selectedBooks.contains(sach.getMaSach())) {
                tongtien -= SLHT * Long.parseLong(sach.getDonGiaBan()); // Trừ tiền trước khi tăng số lượng
            }
            sach.setSoLuong(String.valueOf(SLHT + 1)); // Tăng số lượng
            // Nếu sản phẩm đang được chọn, cộng lại tổng tiền với số lượng mới
            if (selectedBooks.contains(sach.getMaSach())) {
                tongtien += Integer.parseInt(sach.getSoLuong()) * Long.parseLong(sach.getDonGiaBan());

                // Gọi callback cập nhật tổng tiền
                if (totalPriceChangeListener != null) {
                    totalPriceChangeListener.onTotalPriceChange(tongtien);
                }
            }
            holder.soluong.setText(sach.getSoLuong());
//            notifyItemChanged(position); // Cập nhật lại giao diện cho item này
            updateSoLuongSach(LoginActivity.MaGioHang,sach.getMaSach(),sach.getSoLuong());
        });

        // Xử lý nút giảm số lượng
        holder.minus.setOnClickListener(v -> {
            int SLHT = Integer.parseInt(sach.getSoLuong());
            if (SLHT > 1) {
                // Nếu sản phẩm đang được chọn, cập nhật tổng tiền
                if (selectedBooks.contains(sach.getMaSach())) {
                    tongtien -= SLHT * Long.parseLong(sach.getDonGiaBan()); // Trừ tiền trước khi giảm số lượng
                }
                sach.setSoLuong(String.valueOf(SLHT - 1));
                // Nếu sản phẩm đang được chọn, cộng lại tổng tiền với số lượng mới
                if (selectedBooks.contains(sach.getMaSach())) {
                    tongtien += Integer.parseInt(sach.getSoLuong()) * Long.parseLong(sach.getDonGiaBan());

                    // Gọi callback cập nhật tổng tiền
                    if (totalPriceChangeListener != null) {
                        totalPriceChangeListener.onTotalPriceChange(tongtien);
                    }
                }
                holder.soluong.setText(sach.getSoLuong());// Giảm số lượng nếu lớn hơn 1
//                notifyItemChanged(position); // Cập nhật lại giao diện cho item này
                updateSoLuongSach(LoginActivity.MaGioHang,sach.getMaSach(),sach.getSoLuong());
            }
        });
        // Đặt trạng thái checkbox dựa trên danh sách selectedBooks
        holder.checkBox.setChecked(selectedBooks.contains(sach.getMaSach()));
        // Gắn sự kiện cho CheckBox
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Thêm mã sách vào danh sách nếu được chọn
//                selectedBooks.add(sach.getMaSach());
                if (!selectedBooks.contains(sach.getMaSach())) {
                    selectedBooks.add(sach.getMaSach());
                    tongtien+=Long.parseLong(sach.getSoLuong())*Long.parseLong(sach.getDonGiaBan());
                }
            }
            else {
                // Xóa mã sách khỏi danh sách nếu bỏ chọn
                selectedBooks.remove(sach.getMaSach());
                tongtien-=Long.parseLong(sach.getSoLuong())*Long.parseLong(sach.getDonGiaBan());
            }
            // Gọi callback cập nhật tổng tiền
            if (totalPriceChangeListener != null) {
                totalPriceChangeListener.onTotalPriceChange(tongtien);
            }
            // Kiểm tra lại danh sách selectedBooks để đảm bảo mã sách được lưu
            Log.d("SelectedBooks", "Danh sách sách đã chọn: " + selectedBooks.toString());
        });
    }
    private void updateSoLuongSach(String maGH ,String maSach, String soLuongMoi) {
        // Gửi request lên server để cập nhật số lượng
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.updateSoLuong(maGH,maSach, soLuongMoi);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("API Cập Nhật Số Lượng", apiResponse.getMessage());
                } else {
                    Log.d("API Cập Nhật Số Lượng", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<APICapNhat> call, Throwable t) {
                Log.d("API", "Lỗi kết nối");
            }
        });
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
    public ArrayList<String> getSelectedBooks() {
        return selectedBooks; // Trả về danh sách mã sách được chọn
    }
    public static void resetSelectedBooks() {
        selectedBooks.clear();
    }
    public String getTongTien() {
        return formatCurrency(tongtien); // Trả về danh sách mã sách được chọn
    }
    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public static class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, dongia, soluong,minus,plus;
        ImageView hinhanh;
        CheckBox checkBox;
        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensach);
            dongia = itemView.findViewById(R.id.dongia);
            soluong = itemView.findViewById(R.id.numOrder);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            hinhanh = itemView.findViewById(R.id.hinhanh);
            checkBox=itemView.findViewById(R.id.checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Gọi hàm onItemClickListener nếu cần
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}