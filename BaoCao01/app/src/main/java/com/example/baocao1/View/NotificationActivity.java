package com.example.baocao1.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.ThongBaoAdapter;
import com.example.baocao1.Model.ThongBao;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    private ImageView icon_back;
    private RecyclerView recyclerViewThongBao;
    private ThongBaoAdapter adapter;
    private List<ThongBao> thongBaoList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongbao_user);
        recyclerViewThongBao = findViewById(R.id.recyclerViewThongBao);
        recyclerViewThongBao.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        thongBaoList = new ArrayList<>();
        adapter = new ThongBaoAdapter(thongBaoList);
        recyclerViewThongBao.setAdapter(adapter);
        fetchThongBao(LoginActivity.MaKhachHang);
        icon_back = findViewById(R.id.icon_home);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void fetchThongBao(String makh) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<ThongBao>> call = apiService.getThongBao(makh);
        call.enqueue(new Callback<List<ThongBao>>() {
            @Override
            public void onResponse(Call<List<ThongBao>> call, Response<List<ThongBao>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    thongBaoList.clear();
                    thongBaoList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext().getApplicationContext(), "API Thông báo trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ThongBao>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(getApplicationContext().getApplicationContext(), "Không thể gọi API Thông báo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}