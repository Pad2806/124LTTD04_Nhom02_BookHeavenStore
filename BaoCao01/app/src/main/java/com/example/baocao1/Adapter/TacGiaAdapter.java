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
import com.example.baocao1.Model.TacGia;
import com.example.baocao1.R;

import java.util.List;

public class TacGiaAdapter extends RecyclerView.Adapter<TacGiaAdapter.NoiBatViewHolder> {
    private List<TacGia> tacGiaList;
    private static OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClickTacGia(View view, int position, long id);
    }
    public TacGiaAdapter(List<TacGia> tacGiaList, OnItemClickListener onItemClickListener) {
        this.tacGiaList = tacGiaList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public NoiBatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_author, parent, false);
        return new NoiBatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoiBatViewHolder holder, int position) {
        TacGia tacGia= tacGiaList.get(position);
        // Load image using Glide
        String imageUrl = tacGia.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.anhTG);
        holder.tenTG.setText(tacGia.getTenTacGia());
        holder.slTP.setText(tacGia.getSoLuongTP());

    }
    @Override
    public int getItemCount() {
        return tacGiaList.size();
    }

    public static class NoiBatViewHolder extends RecyclerView.ViewHolder {
        TextView tenTG, slTP;
        ImageView anhTG;

        public NoiBatViewHolder(View itemView) {
            super(itemView);
            tenTG = itemView.findViewById(R.id.tenTG);
            slTP = itemView.findViewById(R.id.slTP);
            anhTG = itemView.findViewById(R.id.anhTG);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickTacGia(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}