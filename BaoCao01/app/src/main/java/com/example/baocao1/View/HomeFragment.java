package com.example.baocao1.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.SachNoiBatAdapter;
import com.example.baocao1.Adapter.TheLoaiAdapter;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.Model.TheLoai;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SachNoiBatAdapter.OnItemClickListener,TheLoaiAdapter.OnItemClickListener {
    View view;
    private ImageView icon_noti;
    private TextView xinchao;
    private RecyclerView recyclerViewNoiBat,recyclerViewTheLoai;
    private SachNoiBatAdapter adapterNB;
    private List<ChiTietSach> sanPhamList;
    private TheLoaiAdapter adapterTL;
    private List<TheLoai> theLoaiList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        xinchao=view.findViewById(R.id.xinchao);
        recyclerViewNoiBat = view.findViewById(R.id.recyclerViewNoiBat);
        recyclerViewNoiBat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNoiBat.setAdapter(adapterNB);
        sanPhamList = new ArrayList<>();
        adapterNB = new SachNoiBatAdapter(sanPhamList,this);
        recyclerViewNoiBat.setAdapter(adapterNB);

        recyclerViewTheLoai = view.findViewById(R.id.recyclerViewTheLoai);
        recyclerViewTheLoai.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerViewTheLoai.setAdapter(adapterTL);
        theLoaiList = new ArrayList<>();
        adapterTL = new TheLoaiAdapter(theLoaiList,this);
        recyclerViewTheLoai.setAdapter(adapterTL);

        // Gọi API để lấy dữ liệu
        fetchSanPhams();
        fetchTheLoais();
        icon_noti = view.findViewById(R.id.icon_noti);
        icon_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), NotificationActivity.class));
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(AccountFragment.TenKhachHang.isEmpty())
            xinchao.setText("Xin chào "+LoginActivity.TenKhachHang);
        else
            xinchao.setText("Xin chào "+AccountFragment.TenKhachHang);
    }
    private void fetchSanPhams() {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<ChiTietSach>> call = apiService.getSPNoiBat();
        call.enqueue(new Callback<List<ChiTietSach>>() {
            @Override
            public void onResponse(Call<List<ChiTietSach>> call, Response<List<ChiTietSach>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sanPhamList.clear();
                    sanPhamList.addAll(response.body());
                    adapterNB.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "API Nổi bật trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietSach>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getActivity().getApplicationContext(), "Không thể gọi API Nổi bật", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchTheLoais() {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<TheLoai>> call = apiService.getTheLoai();
        call.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    theLoaiList.clear();
                    theLoaiList.addAll(response.body());
                    adapterTL.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "API Thể loại trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getActivity().getApplicationContext(), "Không thể gọi API Thể loại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClickNoiBat(View view, int position, long id) {
        ChiTietSach sach = sanPhamList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", sach.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
    @Override
    public void onItemClickTheLoai(View view, int position, long id) {
        TheLoai sach = theLoaiList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getContext(), ClassifyBookActivity.class);
        intent.putExtra("MaTheLoai", sach.getMaTheLoai()); // Truyền mã sản phẩm
        intent.putExtra("TenTheLoai", sach.getTenTheLoai());
        startActivity(intent);
    }
}