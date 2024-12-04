package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.DonMuaAdapter;
import com.example.baocao1.Model.DonHang;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderedHistory3011Fragment extends Fragment  implements DonMuaAdapter.OnItemClickListener{
    private RecyclerView recyclerViewDonMua;
    private DonMuaAdapter adapter;
    private List<DonHang> donHangList;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ordered_history3011, container, false);
        recyclerViewDonMua = view.findViewById(R.id.recyclerViewDonMua);
        recyclerViewDonMua.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewDonMua.setAdapter(adapter);
        donHangList = new ArrayList<>();
        adapter = new DonMuaAdapter(donHangList,this,getContext());
        recyclerViewDonMua.setAdapter(adapter);

        fetchDonNhan(LoginActivity.MaKhachHang);
    return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Gọi lại hàm để tải lại dữ liệu
        fetchDonNhan(LoginActivity.MaKhachHang);
    }
    private void fetchDonNhan(String MaKhachHang) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<DonHang>> call = apiService.getDonMua(MaKhachHang);
        call.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    donHangList.clear();
                    donHangList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext(), "API Đơn đặt trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getContext(), "Không thể gọi API Đơn đặt", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClickDonMua(View view, int position, long id) {
        DonHang donhang = donHangList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", donhang.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
}