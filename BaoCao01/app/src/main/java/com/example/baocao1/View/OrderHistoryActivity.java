//package com.example.baocao1.View;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.baocao1.API.ApiController;
//import com.example.baocao1.API.ApiService;
//import com.example.baocao1.Adapter.DonMuaAdapter;
//import com.example.baocao1.Model.DonHang;
//import com.example.baocao1.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class OrderHistoryActivity extends AppCompatActivity implements DonMuaAdapter.OnItemClickListener {
//    private ImageView icon_back;
//    private LinearLayout btnCancelOrder;
//    Dialog dialog, dialog1;
//    private TextView btnCancel, btnAgree;
//    private RecyclerView recyclerViewDonMua;
//    private DonMuaAdapter adapter;
//    private List<DonHang> donHangList;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_dondat_user);
//
//        icon_back = findViewById(R.id.icon_back);
////        btnCancelOrder = findViewById(R.id.btnCancel);
//
//
//        recyclerViewDonMua = findViewById(R.id.recyclerViewDonMua);
//        recyclerViewDonMua.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerViewDonMua.setAdapter(adapter);
//        donHangList = new ArrayList<>();
//        adapter = new DonMuaAdapter(donHangList,this);
//        recyclerViewDonMua.setAdapter(adapter);
//
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.layout_dialog_cancelorder);
//        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
//        dialog.setCancelable(false);
//
//        dialog1 = new Dialog(this);
//        dialog1.setContentView(R.layout.layout_dialog_cancelordersuccessed);
//        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
//        dialog1.setCancelable(false);
//
//        btnCancel = dialog.findViewById(R.id.btnCancel);
//        btnAgree = dialog.findViewById(R.id.buyAgree);
//
//        icon_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               finish();
//            }
//        });
//
////        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                dialog.show();
////            }
////        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
//            }
//        });
//
//        btnAgree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                dialog1.show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog1.dismiss();
//                        startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
//                    }
//                }, 1000);
//            }
//        });
//        fetchDonNhan(LoginActivity.MaKhachHang);
//
//    }
//    private void fetchDonNhan(String MaKhachHang) {
//        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
//        Call<List<DonHang>> call = apiService.getDonMua(MaKhachHang);
//        call.enqueue(new Callback<List<DonHang>>() {
//            @Override
//            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    donHangList.clear();
//                    donHangList.addAll(response.body());
//                    adapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(getApplicationContext().getApplicationContext(), "API Đơn đặt trả về lỗi", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DonHang>> call, Throwable t) {
//                // Xử lý lỗi khi gọi API thất bại
//                Toast.makeText(getApplicationContext().getApplicationContext(), "Không thể gọi API Đơn đặt", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public void onItemClickDonMua(View view, int position, long id) {
//        DonHang donhang = donHangList.get(position); // Lấy đối tượng Sach từ danh sách sanPhamList theo vị trí
//        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
//        Intent intent = new Intent(getApplicationContext(), DetailsHotBookActivity.class);
//        intent.putExtra("MaSach", donhang.getMaSach()); // Truyền mã sản phẩm
//        startActivity(intent);
//    }
//}