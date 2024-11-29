package com.example.baocao1.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.TacGiaSachAdapter;
import com.example.baocao1.Adapter.TheLoaiSachAdapter;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorsBookActivity extends AppCompatActivity  implements TacGiaSachAdapter.OnItemClickListener{
    private  ImageView icon_back;
    private TextView tentacgia;
    private RecyclerView recyclerViewSachTacGia;
    private TacGiaSachAdapter adapterSach;
    private List<ChiTietSach> sachList;
    private String MaTacGia,TenTacGia;
    @Override
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettacgia_user);
        icon_back = findViewById(R.id.icon_back);

        tentacgia = findViewById(R.id.tentacgia);
        recyclerViewSachTacGia = findViewById(R.id.recyclerViewSachTacGia);
        recyclerViewSachTacGia.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewSachTacGia.setAdapter(adapterSach);
        sachList = new ArrayList<>();
        adapterSach = new TacGiaSachAdapter(sachList,this);
        recyclerViewSachTacGia.setAdapter(adapterSach);
        TenTacGia = getIntent().getStringExtra("TenTacGia");
        tentacgia.setText(TenTacGia);
        MaTacGia = getIntent().getStringExtra("MaTacGia");
        fetchSanPhams(MaTacGia);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(AuthorsBookActivity.this, AuthorFragment.class));
                finish();
            }
        });
    }
    private void fetchSanPhams(String MaTacGia) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<ChiTietSach>> call = apiService.getSach_TacGia(MaTacGia);
        call.enqueue(new Callback<List<ChiTietSach>>() {
            @Override
            public void onResponse(Call<List<ChiTietSach>> call, Response<List<ChiTietSach>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sachList.clear();
                    sachList.addAll(response.body());
                    adapterSach.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(AuthorsBookActivity.this, "API Nổi bật trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietSach>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(AuthorsBookActivity.this, "Không thể gọi API Nổi bật", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClickTacGiaSach(View view, int position, long id) {
        ChiTietSach sach = sachList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getApplicationContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", sach.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
}