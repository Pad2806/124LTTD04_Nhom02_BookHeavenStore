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
import com.example.baocao1.Model.TheLoai;
import com.example.baocao1.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {
    private List<TheLoai> theLoais;
    private static OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClickTheLoai(View view, int position, long id);
    }
    public TheLoaiAdapter(List<TheLoai> theLoais, OnItemClickListener onItemClickListener) {
        this.theLoais = theLoais;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai_trangchu, parent, false);
        return new TheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {
        TheLoai theLoai = theLoais.get(position);
        // Load image using Glide
        String imageUrl = theLoai.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.anhTL);
        holder.tenTL.setText(theLoai.getTenTheLoai());
    }
    @Override
    public int getItemCount() {
        return theLoais.size();
    }

    public static class TheLoaiViewHolder extends RecyclerView.ViewHolder {
        TextView tenTL;
        ImageView anhTL;

        public TheLoaiViewHolder(View itemView) {
            super(itemView);
            tenTL = itemView.findViewById(R.id.tenTL);
            anhTL = itemView.findViewById(R.id.anhTL);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickTheLoai(v, getAdapterPosition(), getItemId());
                    }
                }
            });
        }
    }
}