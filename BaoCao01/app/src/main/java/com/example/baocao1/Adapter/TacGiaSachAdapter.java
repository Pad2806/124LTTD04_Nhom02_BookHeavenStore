package com.example.baocao1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class TacGiaSachAdapter extends RecyclerView.Adapter<TacGiaSachAdapter.NoiBatViewHolder> {
    private List<ChiTietSach> dsSach;
    private static OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClickTacGiaSach(View view, int position, long id);
    }
    public TacGiaSachAdapter(List<ChiTietSach> dsSach, OnItemClickListener onItemClickListener) {
        this.dsSach = dsSach;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public NoiBatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloaisach, parent, false);
        return new NoiBatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoiBatViewHolder holder, int position) {
        ChiTietSach sanPham = dsSach.get(position);
        // Load image using Glide
        String imageUrl = sanPham.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.hinhanh);
//        holder.tenNB.setText(sanPham.getHinhAnh());
        holder.tensach.setText(sanPham.getTenSach());
        holder.giaban.setText(formatCurrency(Long.parseLong(sanPham.getDonGiaBan())));
        holder.slban.setText("Đã bán: "+(sanPham.getSoLuongBan()));

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
        return dsSach.size();
    }

    public static class NoiBatViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, giaban,slban;
        ImageView hinhanh;

        public NoiBatViewHolder(View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensach);
            giaban = itemView.findViewById(R.id.giaban);
            slban = itemView.findViewById(R.id.slban);
            hinhanh = itemView.findViewById(R.id.hinhanh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickTacGiaSach(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}