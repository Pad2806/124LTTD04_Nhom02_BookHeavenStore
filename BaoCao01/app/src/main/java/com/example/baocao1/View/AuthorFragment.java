package com.example.baocao1.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.TacGiaAdapter;
import com.example.baocao1.Model.TacGia;
import com.example.baocao1.Model.TheLoai;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorFragment extends Fragment implements TacGiaAdapter.OnItemClickListener{
    View view;
    private RecyclerView recyclerViewTacGia;
    private TacGiaAdapter adapterTG;
    private List<TacGia> tacGiaList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_author, container, false);
        recyclerViewTacGia = view.findViewById(R.id.recyclerViewTacGia);
        recyclerViewTacGia.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        tacGiaList = new ArrayList<>();
        adapterTG = new TacGiaAdapter(tacGiaList,this);
        recyclerViewTacGia.setAdapter(adapterTG);
        fetchTacGias();
        return view;
    }
    private void fetchTacGias() {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<TacGia>> call = apiService.getTacGia();
        call.enqueue(new Callback<List<TacGia>>() {
            @Override
            public void onResponse(Call<List<TacGia>> call, Response<List<TacGia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tacGiaList.clear();
                    tacGiaList.addAll(response.body());
                    adapterTG.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "API Tác Giả trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<TacGia>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getActivity().getApplicationContext(), "Không thể gọi API Tác Giả", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClickTacGia(View view, int position, long id) {
        TacGia tacgia = tacGiaList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getContext(), AuthorsBookActivity.class);
        intent.putExtra("MaTacGia", tacgia.getMaTacGia()); // Truyền mã sản phẩm
        intent.putExtra("TenTacGia", tacgia.getTenTacGia());
        startActivity(intent);
    }
}
