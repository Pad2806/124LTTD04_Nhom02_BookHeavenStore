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

public class DonMuaAdapter extends RecyclerView.Adapter<DonMuaAdapter.DonDatViewHolder> {
    private List<DonHang> donHangList;
    private static OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClickDonMua(View view, int position, long id);
    }

        public DonMuaAdapter(List<DonHang> donHangList, OnItemClickListener onItemClickListener) {
        this.donHangList = donHangList;
        this.onItemClickListener=onItemClickListener;
    }
//    public DonMuaAdapter(List<DonHang> donHangList) {
//        this.donHangList = donHangList;
//    }

    @NonNull
    @Override
    public DonDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donmua, parent, false);
        return new DonDatViewHolder(view);
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

    public static class DonDatViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, dongia, soluong,giodat,ngaydat;
        ImageView hinhanh;

        public DonDatViewHolder(View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensachDM);
            dongia = itemView.findViewById(R.id.dongiaDM);
            soluong = itemView.findViewById(R.id.soluongDM);
            hinhanh = itemView.findViewById(R.id.hinhanhDM);
            giodat = itemView.findViewById(R.id.giodat);
            ngaydat = itemView.findViewById(R.id.ngaydat);
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