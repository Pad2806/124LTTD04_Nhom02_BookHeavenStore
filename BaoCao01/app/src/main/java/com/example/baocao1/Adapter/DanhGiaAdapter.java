package com.example.baocao1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baocao1.Model.DanhGiaKH;
import com.example.baocao1.Model.TacGia;
import com.example.baocao1.R;

import java.util.List;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.DanhGiaViewHolder> {
    private List<DanhGiaKH> danhGiaKHS;
    public DanhGiaAdapter(List<DanhGiaKH> danhGiaKHS) {
        this.danhGiaKHS = danhGiaKHS;
    }

    @NonNull
    @Override
    public DanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhgia, parent, false);
        return new DanhGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaViewHolder holder, int position) {
        DanhGiaKH danhGia= danhGiaKHS.get(position);
        holder.tenkh.setText(danhGia.getTenKhachHang());
        holder.danhgia.setText(danhGia.getDanhGia()+" sao");
        holder.noidung.setText(danhGia.getNoiDung());
    }
    @Override
    public int getItemCount() {
        return danhGiaKHS.size();
    }

    public static class DanhGiaViewHolder extends RecyclerView.ViewHolder {
        TextView tenkh,danhgia,noidung;

        public DanhGiaViewHolder(View itemView) {
            super(itemView);
            tenkh = itemView.findViewById(R.id.tenkh);
            danhgia = itemView.findViewById(R.id.danhgia);
            noidung = itemView.findViewById(R.id.noidung);
        }
    }
}