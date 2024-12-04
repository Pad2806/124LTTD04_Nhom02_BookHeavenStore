package com.example.baocao1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baocao1.Model.ThongBao;
import com.example.baocao1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ThongBaoViewHolder> {
    private List<ThongBao> thongBao;
    public ThongBaoAdapter(List<ThongBao> thongBao) {
        this.thongBao = thongBao;
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongbao, parent, false);
        return new ThongBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        ThongBao ThongBao= thongBao.get(position);
        holder.giotb.setText(ThongBao.getGioTB());
        holder.ngaytb.setText(convertDateFormat(ThongBao.getNgayTB()));
        holder.noidungtb.setText(ThongBao.getNoiDungTB());
    }
    @Override
    public int getItemCount() {
        return thongBao.size();
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

    public static class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        TextView giotb,ngaytb,noidungtb;

        public ThongBaoViewHolder(View itemView) {
            super(itemView);
            giotb = itemView.findViewById(R.id.giotb);
            ngaytb = itemView.findViewById(R.id.ngaytb);
            noidungtb = itemView.findViewById(R.id.noidungtb);
        }
    }
}