package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.DonNhanAdapter;
import com.example.baocao1.Model.DonHang;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoughtHistoryFragment extends Fragment implements DonNhanAdapter.OnItemClickListener{
    private ImageView icon_cancel;
    Dialog dialog;
    private RatingBar ratingBar;
    private RecyclerView recyclerViewDonNhan;
    private DonNhanAdapter adapter;
    private List<DonHang> donHangList;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_bought_history, container, false);
        recyclerViewDonNhan = view.findViewById(R.id.recyclerViewDonNhan);
        recyclerViewDonNhan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewDonNhan.setAdapter(adapter);
        donHangList = new ArrayList<>();
        adapter = new DonNhanAdapter(donHangList,this);
        recyclerViewDonNhan.setAdapter(adapter);



        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_dialog_rating);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dialog_whiterounded));
        dialog.setCancelable(false);

        icon_cancel = dialog.findViewById(R.id.icon_cancel);
        ratingBar = dialog.findViewById(R.id.ratingBar);

        icon_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BoughtHistoryFragment.this.getClass()));
            }
        });
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
        Call<List<DonHang>> call = apiService.getDonNhan(MaKhachHang);
        call.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    donHangList.clear();
                    donHangList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "API Đơn nhận trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getContext().getApplicationContext(), "Không thể gọi API Đơn nhận", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBuyAgainClick(DonHang donhang) {
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra("From", "BoughtHistoryFragment");
        intent.putExtra("MaSach", donhang.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }

    @Override
    public void onRateClick(DonHang donhang) {
        Intent intent = new Intent(getContext(), RatingActivity.class);
        intent.putExtra("MaDanhGia", donhang.getMaDanhGia()); // Truyền mã sản phẩm
        startActivity(intent);
    }
}