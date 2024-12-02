//package com.example.baocao1.View;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.baocao1.API.ApiController;
//import com.example.baocao1.API.ApiService;
//import com.example.baocao1.Adapter.DonNhanAdapter;
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
//public class BuyHistoryActivity extends AppCompatActivity implements DonNhanAdapter.OnItemClickListener{
//    private ImageView icon_back, icon_cancel;
//    private TextView btnRate, btnBuyAgain;
//    Dialog dialog;
//    private RatingBar ratingBar;
//    private RecyclerView recyclerViewDonNhan;
//    private DonNhanAdapter adapter;
//    private List<DonHang> donHangList;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_donmua_user);
//
//        icon_back = findViewById(R.id.icon_back);
////        btnBuyAgain = findViewById(R.id.btnBuyAgain);
////        btnRate = findViewById(R.id.btnRate);
//
//        recyclerViewDonNhan = findViewById(R.id.recyclerViewDonNhan);
//        recyclerViewDonNhan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerViewDonNhan.setAdapter(adapter);
//        donHangList = new ArrayList<>();
//        adapter = new DonNhanAdapter(donHangList,this);
//        recyclerViewDonNhan.setAdapter(adapter);
//
//
//
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.layout_dialog_rating);
//        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_whiterounded));
//        dialog.setCancelable(false);
//
//        icon_cancel = dialog.findViewById(R.id.icon_cancel);
//        ratingBar = dialog.findViewById(R.id.ratingBar);
//
//        icon_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
////        btnBuyAgain.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                startActivity(new Intent(BuyHistoryActivity.this, PaymentActivity.class));
////            }
////        });
////
////        btnRate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                dialog.show();
////                startActivity(new Intent(BuyHistoryActivity.this,RatingActivity.class) );
////            }
////        });
////
////        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
////            @Override
////            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
////                if (fromUser) {
////                    Toast.makeText(getApplicationContext(), "Bạn đã đánh giá: " + rating + " sao cho đơn hàng", Toast.LENGTH_SHORT).show();
////                }
////                Toast.makeText(getApplicationContext(), "Cảm ơn bạn đã đánh giá!", Toast.LENGTH_SHORT).show();
////                new Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        dialog.dismiss();
////                    }
////                }, 3000);
////            }
////        });
//
//        icon_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(BuyHistoryActivity.this, BuyHistoryActivity.class));
//            }
//        });
//        fetchDonNhan(LoginActivity.MaKhachHang);
//    }
//    private void fetchDonNhan(String MaKhachHang) {
//        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
//        Call<List<DonHang>> call = apiService.getDonNhan(MaKhachHang);
//        call.enqueue(new Callback<List<DonHang>>() {
//            @Override
//            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    donHangList.clear();
//                    donHangList.addAll(response.body());
//                    adapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(getApplicationContext().getApplicationContext(), "API Đơn nhận trả về lỗi", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DonHang>> call, Throwable t) {
//                // Xử lý lỗi khi gọi API thất bại
//                Toast.makeText(getApplicationContext().getApplicationContext(), "Không thể gọi API Đơn nhận", Toast.LENGTH_SHORT).show();
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