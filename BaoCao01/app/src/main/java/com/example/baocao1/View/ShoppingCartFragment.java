package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.SachNoiBatAdapter;
import com.example.baocao1.Adapter.ShoppingCartAdapter;
import com.example.baocao1.Adapter.TheLoaiAdapter;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.Model.TacGia;
import com.example.baocao1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartFragment extends Fragment implements ShoppingCartAdapter.OnItemClickListener{
    View view;
    private int count = 1;
    private TextView plus, minus, numOrder, btnBuy, btnDel, btnCancel, btnAgree;
    public static TextView totalPrice;
    Dialog dialog, dialog1;
    private RecyclerView recyclerViewGioHang;
    private ShoppingCartAdapter adapterGH;
    private List<Sach_GioHang> sachGioHang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        btnBuy = view.findViewById(R.id.btnBuy);
        btnDel = view.findViewById(R.id.btnDel);
        totalPrice = view.findViewById(R.id.totalPrice);

        recyclerViewGioHang = view.findViewById(R.id.recyclerViewGioHang);
        recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewGioHang.setAdapter(adapterGH);
        sachGioHang = new ArrayList<>();
        adapterGH = new ShoppingCartAdapter(sachGioHang,this);
        adapterGH.setOnTotalPriceChangeListener(new ShoppingCartAdapter.OnTotalPriceChangeListener() {
            @Override
            public void onTotalPriceChange(long totalPrices) {
                // Hiển thị tổng tiền
                totalPrice.setText(ShoppingCartAdapter.formatCurrency(totalPrices));
            }
        });
        recyclerViewGioHang.setAdapter(adapterGH);
        fetchGioHang(LoginActivity.MaGioHang);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.layout_dialog_deleteshoppingcart);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog.setCancelable(false);

        dialog1 = new Dialog(requireContext());
        dialog1.setContentView(R.layout.layout_dialog_delscsuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog1.setCancelable(false);

        btnAgree = dialog.findViewById(R.id.buyAgree);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaymentActivity.class);
                String selectedBooks = String.join(",", adapterGH.getSelectedBooks());
                intent.putExtra("DSSach", selectedBooks);
//                Toast.makeText(getContext(), "DSSach: "+selectedBooks, Toast.LENGTH_SHORT).show();
                intent.putExtra("From", "ShoppingCartFragment");
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //Gọi hàm xóa sản phẩm
                String selectedBooks = String.join(",", adapterGH.getSelectedBooks());
                xoaSach(selectedBooks);
                dialog1.show();
                // Đợi cho việc xóa hoàn tất, sau đó làm mới giỏ hàng
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fetchGioHang(LoginActivity.MaGioHang);  // Chỉ gọi fetchGioHang khi xóa thành công
                        if(!totalPrice.getText().toString().replace("đ","").equals("0")){
                            reset();
                        }
                        dialog1.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog1.dismiss();
                            }
                        }, 1000);
                    }
                }, 500);  // Delay nhỏ để chắc chắn rằng xóa đã hoàn tất
            }
        });
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        ShoppingCartAdapter.resetSelectedBooks();
        reset();
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchGioHang(LoginActivity.MaGioHang);
    }
    public static void reset(){
        ShoppingCartAdapter.tongtien=0;
        totalPrice.setText(ShoppingCartAdapter.formatCurrency(0));
    }
    private void xoaSach(String sachlist) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.xoaSach_GioHang(sachlist);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Xóa sách thành công", apiResponse.getMessage());
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Xóa sách thất bại", "Error: " + response.message());
//                    Toast.makeText(getApplicationContext(), "onResponse"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APICapNhat> call, Throwable t) {
                Log.d("API", "Lỗi kết nối"+t.getMessage());
//                Toast.makeText(getApplicationContext(), "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchGioHang(String MaGioHang) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<Sach_GioHang>> call = apiService.getGioHang(MaGioHang);
        call.enqueue(new Callback<List<Sach_GioHang>>() {
            @Override
            public void onResponse(Call<List<Sach_GioHang>> call, Response<List<Sach_GioHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sachGioHang.clear();
                    sachGioHang.addAll(response.body());
                    adapterGH.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "API Tác Giả trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sach_GioHang>> call, Throwable t) {
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getActivity().getApplicationContext(), "Không thể gọi API Tác Giả", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemClick(View view, int position, long id) {
        Sach_GioHang sach = sachGioHang.get(position);
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", sach.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
}