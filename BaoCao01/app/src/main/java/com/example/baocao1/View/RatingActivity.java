package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.Model.Sach_DanhGia;
import com.example.baocao1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {
    private ImageView icon_back,hinhanh;
    private TextView btnGui,tensach,tacgia;
    private EditText noidung;
    private RatingBar danhgiasp;
    Dialog dialog;
    private Sach_DanhGia sach;
    private RatingBar ratingbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgiasanpham);
        icon_back = findViewById(R.id.icon_back);
        btnGui = findViewById(R.id.btnGui);
        hinhanh = findViewById(R.id.hinhanh);
        tensach = findViewById(R.id.tensach);
        tacgia = findViewById(R.id.tacgia);
        noidung = findViewById(R.id.noidung);
        danhgiasp = findViewById(R.id.danhgiasp);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(RatingActivity.this, BuyHistoryActivity.class));
                finish();
            }
        });
//        Toast.makeText(getApplicationContext(), getIntent().getStringExtra("MaDanhGia"), Toast.LENGTH_SHORT).show();
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noidungdg= noidung.getText().toString();
                if(noidungdg.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập nội dung đánh giá!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String madanhgia = getIntent().getStringExtra("MaDanhGia");
                            String diemdanhgia = String.valueOf(danhgiasp.getNumStars());
                            danhgia(madanhgia, diemdanhgia, noidungdg);
//                        startActivity(new Intent(RatingActivity.this, BuyHistoryActivity.class));
                            finish();
                        }
                    }, 1500);
                }
            }
        });
        fetchDanhGiaSach(getIntent().getStringExtra("MaDanhGia"));
    }
    private void danhgia(String madanhgia,String danhgia, String noidung) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.DanhGia(madanhgia,danhgia,noidung);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Thành công", apiResponse.getMessage()+madanhgia+" "+danhgia+" "+noidung);
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Thất bại", "Error: " + response.message()+madanhgia+" "+danhgia+" "+noidung);
//                    Toast.makeText(getApplicationContext(), "onResponse"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APICapNhat> call, Throwable t) {
                Log.d("API", "Lỗi kết nối đánh giá"+t.getMessage());
//                Toast.makeText(getApplicationContext(), "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchDanhGiaSach(String madanhgia) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<Sach_DanhGia> call = apiService.getDanhGiaSach(madanhgia);
        call.enqueue(new Callback<Sach_DanhGia>() {
            @Override
            public void onResponse(Call<Sach_DanhGia> call, Response<Sach_DanhGia> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sach = response.body();
                    String imageUrl = sach.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
                    Glide.with(RatingActivity.this)
                            .load(imageUrl)
                            .into(hinhanh);
                    tensach.setText(sach.getTenSach());
                    tacgia.setText(sach.getTenTacGia());
                    if(!sach.getDanhGia().equals("0")){
                        danhgiasp.setRating(Float.parseFloat(sach.getDanhGia()));
                    }
                    if(!sach.getNoiDung().equals("NULL")){
                        noidung.setText(sach.getNoiDung());
                    }
                } else {
                    Toast.makeText(RatingActivity.this, "Không thể tải chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Sach_DanhGia> call, Throwable t) {
                Log.d("Lỗi",t.getMessage());
                Toast.makeText(RatingActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}