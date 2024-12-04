package com.example.baocao1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baocao1.Model.DonHang;
import com.example.baocao1.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DonNhanAdapter extends RecyclerView.Adapter<DonNhanAdapter.DonMuaViewHolder> {
    private List<DonHang> donHangList;
    private static OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClickDonMua(View view, int position, long id);
        void onBuyAgainClick(DonHang donHang);
        void onRateClick(DonHang donhang);
    }

        public DonNhanAdapter(List<DonHang> donHangList, OnItemClickListener onItemClickListener) {
        this.donHangList = donHangList;
        this.onItemClickListener=onItemClickListener;
    }
//    public DonMuaAdapter(List<DonHang> donHangList) {
//        this.donHangList = donHangList;
//    }

    @NonNull
    @Override
    public DonMuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donnhan, parent, false);
        return new DonMuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonMuaViewHolder holder, int position) {
        DonHang donhang = donHangList.get(position);
        // Load image using Glide
        String imageUrl = donhang.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.hinhanh);
        holder.tensach.setText(donhang.getTenSach());
        holder.dongia.setText(formatCurrency(Long.parseLong(donhang.getDonGiaBan())));
        holder.soluong.setText("Số lượng: "+donhang.getSoLuong());
        holder.ngaynhan.setText(convertDateFormat(donhang.getNgayGiaoHang()));
        holder.btnBuyAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onBuyAgainClick(donhang);
                }
            }
        });
        if(!donhang.getDanhGia().equals("0") && !donhang.getNoiDung().equals("NULL")){
            holder.btnRate.setVisibility(View.INVISIBLE);
        }else{
            holder.btnRate.setVisibility(View.VISIBLE);
            holder.btnRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onRateClick(donhang);
                    }
                }
            });
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

    public static class DonMuaViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, dongia, soluong, ngaynhan,btnRate,btnBuyAgain;
        ImageView hinhanh;

        public DonMuaViewHolder(View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensachDN);
            dongia = itemView.findViewById(R.id.dongiaDN);
            soluong = itemView.findViewById(R.id.soluongDN);
            hinhanh = itemView.findViewById(R.id.hinhanhDN);
            ngaynhan = itemView.findViewById(R.id.ngaynhan);
            btnRate = itemView.findViewById(R.id.btnRate);
            btnBuyAgain = itemView.findViewById(R.id.btnBuyAgain);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickDonMua(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}