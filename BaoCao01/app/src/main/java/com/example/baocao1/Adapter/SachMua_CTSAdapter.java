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
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.R;
import com.example.baocao1.View.LoginActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class SachMua_CTSAdapter extends RecyclerView.Adapter<SachMua_CTSAdapter.SachMua_CTSViewHolder> {
    private List<ChiTietSach> sachList;
    private static OnItemClickListener onItemClickListener;
    private long tongtien=0;
    public static String soLuong,donGia;
    public static ChiTietSach tietSach;
    public List<ChiTietSach> getSachList() {
        return sachList;
    }
    private OnTotalPriceChangeListener totalPriceChangeListener;
    public interface OnTotalPriceChangeListener {
        void onTotalPriceChange(long totalPrices);
    }
    public void setOnTotalPriceChangeListener(OnTotalPriceChangeListener listener) {
        this.totalPriceChangeListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClickSachMua_CTS(View view, int position, long id);
    }

    public SachMua_CTSAdapter(List<ChiTietSach> sachList, OnItemClickListener onItemClickListener) {
        this.sachList = sachList;
        this.onItemClickListener=onItemClickListener;
    }
//    public ShoppingCartAdapter(List<Sach_GioHang> sachList) {
//        this.sachList = sachList;
//    }

    @NonNull
    @Override
    public SachMua_CTSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhtoan, parent, false);
        return new SachMua_CTSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachMua_CTSViewHolder holder, int position) {
        ChiTietSach sach = sachList.get(position);
        tietSach=sach;
        // Load image using Glide
        String imageUrl = sach.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.hinhanh);
        holder.tensach.setText(sach.getTenSach());
        sach.setSoLuong("1");
        holder.soluong.setText(sach.getSoLuong());
        holder.dongia.setText(formatCurrency(Long.parseLong(sach.getDonGiaBan())));
        // Nếu là lần đầu tiên hiển thị, tính toán tổng tiền ban đầu
        tongtien += Long.parseLong(sach.getSoLuong()) * Long.parseLong(sach.getDonGiaBan());
        if (totalPriceChangeListener != null) {
            totalPriceChangeListener.onTotalPriceChange(tongtien);
        }
        // Xử lý nút tăng số lượng
        holder.plus.setOnClickListener(v -> {
            int SLHT = Integer.parseInt(sach.getSoLuong());
            tongtien -= SLHT * Long.parseLong(sach.getDonGiaBan());
            sach.setSoLuong(String.valueOf(SLHT + 1)); // Tăng số lượng
            tongtien += Integer.parseInt(sach.getSoLuong()) * Long.parseLong(sach.getDonGiaBan());
            // Gọi callback cập nhật tổng tiền
            if (totalPriceChangeListener != null) {
                totalPriceChangeListener.onTotalPriceChange(tongtien);
            }
            holder.soluong.setText(sach.getSoLuong());
        });
        // Xử lý nút giảm số lượng
        holder.minus.setOnClickListener(v -> {
            int SLHT = Integer.parseInt(sach.getSoLuong());
            if (SLHT > 1) {
                tongtien -= SLHT * Long.parseLong(sach.getDonGiaBan());
                sach.setSoLuong(String.valueOf(SLHT - 1)); // Tăng số lượng
                tongtien += Integer.parseInt(sach.getSoLuong()) * Long.parseLong(sach.getDonGiaBan());

                // Gọi callback cập nhật tổng tiền
                if (totalPriceChangeListener != null) {
                    totalPriceChangeListener.onTotalPriceChange(tongtien);
                }
                holder.soluong.setText(sach.getSoLuong());// Giảm số lượng nếu lớn hơn 1
//                notifyItemChanged(position); // Cập nhật lại giao diện cho item này
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

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public static class SachMua_CTSViewHolder extends RecyclerView.ViewHolder {
        TextView tensach, dongia, soluong,minus,plus;
        ImageView hinhanh;

        public SachMua_CTSViewHolder(View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tensachtt);
            dongia = itemView.findViewById(R.id.dongiatt);
            soluong = itemView.findViewById(R.id.numOrdertt);
            minus = itemView.findViewById(R.id.minustt);
            plus = itemView.findViewById(R.id.plustt);
            hinhanh = itemView.findViewById(R.id.hinhanhtt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickSachMua_CTS(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}