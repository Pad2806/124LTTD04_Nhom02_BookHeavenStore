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

public class SachNoiBatAdapter extends RecyclerView.Adapter<SachNoiBatAdapter.NoiBatViewHolder> {
    private List<ChiTietSach> dsnoibat;
    private static OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClickNoiBat(View view, int position, long id);
    }
    public SachNoiBatAdapter(List<ChiTietSach> dsnoibat, OnItemClickListener onItemClickListener) {
        this.dsnoibat = dsnoibat;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public NoiBatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noibat_trangchu, parent, false);
        return new NoiBatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoiBatViewHolder holder, int position) {
        ChiTietSach sanPham = dsnoibat.get(position);
        // Load image using Glide
        String imageUrl = sanPham.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.anhNB);
//        holder.tenNB.setText(sanPham.getHinhAnh());
        holder.tenNB.setText(sanPham.getTenSach());
        holder.giaNB.setText(formatCurrency(Long.parseLong(sanPham.getDonGiaBan())));
        holder.soluongNB.setText("Đã bán: "+(sanPham.getSoLuongBan()));

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
        return dsnoibat.size();
    }

    public static class NoiBatViewHolder extends RecyclerView.ViewHolder {
        TextView tenNB, giaNB,soluongNB;
        ImageView anhNB;

        public NoiBatViewHolder(View itemView) {
            super(itemView);
            tenNB = itemView.findViewById(R.id.tenNB);
            giaNB = itemView.findViewById(R.id.giaNB);
            soluongNB = itemView.findViewById(R.id.soluongNB);
            anhNB = itemView.findViewById(R.id.anhNB);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickNoiBat(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}