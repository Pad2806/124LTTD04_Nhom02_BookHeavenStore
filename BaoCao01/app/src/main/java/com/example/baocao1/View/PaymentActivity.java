package com.example.baocao1.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.SachMuaAdapter;
import com.example.baocao1.Adapter.SachMua_CTSAdapter;
import com.example.baocao1.Adapter.ShoppingCartAdapter;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.R;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements SachMuaAdapter.OnItemClickListener, SachMua_CTSAdapter.OnItemClickListener{
    private ImageView icon_back, icon_backhome;
    private TextView giamgiatt,vanchuyentt, btnAgree, btnBuy, price, totalPricett,diemtichluy;
    private int count = 1;
    private LinearLayout payLive, payOnl;
    private CheckBox checkBox, checkBox1;
    Dialog buyDialog;
    private ArrayList<String> dsSach;
    private List<Sach_GioHang> listSachMua_GH;
    private List<ChiTietSach> listSachMua_CTS;
    private RecyclerView recyclerViewThanhToan;
    private SachMuaAdapter adapter;
    private SachMua_CTSAdapter adapter_CTS;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan_user);
        icon_back = findViewById(R.id.icon_back);
        icon_backhome = findViewById(R.id.icon_backhome);
        giamgiatt = findViewById(R.id.giamgiatt);
        vanchuyentt = findViewById(R.id.phivctt);
        checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        checkBox1 = findViewById(R.id.checkBox1);
        payLive = findViewById(R.id.payLive);
        payOnl = findViewById(R.id.payOnl);
        btnBuy = findViewById(R.id.orderBook);
        price = findViewById(R.id.price);
        totalPricett = findViewById(R.id.totalPricett);
        recyclerViewThanhToan=findViewById(R.id.recyclerViewThanhToan);
        recyclerViewThanhToan.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        buyDialog = new Dialog(this);
        buyDialog.setContentView(R.layout.layout_dialog_buybook);
        Objects.requireNonNull(buyDialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buyDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        buyDialog.setCancelable(false);

        btnAgree = buyDialog.findViewById(R.id.buyAgree);
        diemtichluy = buyDialog.findViewById(R.id.diemtichluy);
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        icon_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, HomeActivity.class));
            }
        });
        // Đảm bảo rằng khi người dùng nhấp vào mỗi checkbox, chỉ một checkbox được chọn
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox1.setChecked(false); // Nếu checkBox được chọn, bỏ chọn checkBox1
                }
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setChecked(false); // Nếu checkBox1 được chọn, bỏ chọn checkBox
                }
            }
        });
        payLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(true);
                checkBox1.setChecked(false);
            }
        });
        payOnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(false);
                checkBox1.setChecked(true);
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                startActivity(new Intent(PaymentActivity.this,OrderBuyUserActivity.class));
            }
        });
        String source = getIntent().getStringExtra("From");
//        Toast.makeText(getApplicationContext(), source, Toast.LENGTH_SHORT).show();
        if (source != null) {
            if (source.equals("DetailsHotBookActivity")) {
                listSachMua_CTS = new ArrayList<>();
                adapter_CTS = new SachMua_CTSAdapter(listSachMua_CTS,this);
                adapter_CTS.setOnTotalPriceChangeListener(new SachMua_CTSAdapter.OnTotalPriceChangeListener() {
                    @Override
                    public void onTotalPriceChange(long totalPrices) {
                        // Hiển thị tổng tiền
                        price.setText(SachMua_CTSAdapter.formatCurrency(totalPrices));
                        updateTongThanhToan(price.getText().toString(),giamgiatt.getText().toString(),vanchuyentt.getText().toString());
                    }
                });
                recyclerViewThanhToan.setAdapter(adapter_CTS);
                fetchSachMua_ChiTietSach(getIntent().getStringExtra("MaSach"));
            }
            else if (source.equals("BoughtHistoryFragment")) {
                listSachMua_CTS = new ArrayList<>();
                adapter_CTS = new SachMua_CTSAdapter(listSachMua_CTS,this);
                adapter_CTS.setOnTotalPriceChangeListener(new SachMua_CTSAdapter.OnTotalPriceChangeListener() {
                    @Override
                    public void onTotalPriceChange(long totalPrices) {
                        // Hiển thị tổng tiền
                        price.setText(SachMua_CTSAdapter.formatCurrency(totalPrices));
                        updateTongThanhToan(price.getText().toString(),giamgiatt.getText().toString(),vanchuyentt.getText().toString());
                    }
                });
                recyclerViewThanhToan.setAdapter(adapter_CTS);
                fetchSachMua_ChiTietSach(getIntent().getStringExtra("MaSach"));
            }
            else if (source.equals("ShoppingCartFragment")) {
                listSachMua_GH = new ArrayList<>();
                adapter = new SachMuaAdapter(listSachMua_GH,this);
                adapter.setOnTotalPriceChangeListener(new SachMuaAdapter.OnTotalPriceChangeListener() {
                    @Override
                    public void onTotalPriceChange(long totalPrices) {
                        // Hiển thị tổng tiền
                        price.setText(SachMuaAdapter.formatCurrency(totalPrices));
                        updateTongThanhToan(price.getText().toString(),giamgiatt.getText().toString(),vanchuyentt.getText().toString());                    }
                });

                recyclerViewThanhToan.setAdapter(adapter);
                String dsSach = getIntent().getStringExtra("DSSach");
//                Toast.makeText(getApplicationContext(), "DSSach: "+dsSach, Toast.LENGTH_SHORT).show();
                if (dsSach != null && !dsSach.isEmpty()) {
                    fetchSachMua_GioHang(LoginActivity.MaKhachHang,dsSach);
                    Log.d("Danh sách sách: ",LoginActivity.MaKhachHang+"  "+dsSach);
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn lại sách muốn mua", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
        else{
            finish();
        }
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (source != null) {
                    String maKH=LoginActivity.MaKhachHang;
                    String maGH=LoginActivity.MaGioHang;
                    String giamgia=chuyenso(giamgiatt.getText().toString());
                    String phivc=chuyenso(vanchuyentt.getText().toString());
                    String pttt="";
                    if(checkBox.isChecked()){
                        pttt="TTKNH";
                    }
                    if(checkBox1.isChecked()){
                        pttt="TTTTuyen";
                    }
                    String tongtien=chuyenso(totalPricett.getText().toString());
                    if (source.equals("DetailsHotBookActivity")) {
                        String masach= getIntent().getStringExtra("MaSach");
                        String soluong=SachMua_CTSAdapter.tietSach.getSoLuong();
                        String dongia=SachMua_CTSAdapter.tietSach.getDonGiaBan();
                        muaSach_CTS(maKH,masach,soluong,dongia,giamgia,phivc,pttt,tongtien);
                    }
                    else if (source.equals("ShoppingCartFragment")) {
                        SachMuaAdapter adapter = (SachMuaAdapter) recyclerViewThanhToan.getAdapter();
                        List<Sach_GioHang> sachMuaList=new ArrayList<>();
                        if (adapter != null) {
                            // Adapter hợp lệ
                            List<Sach_GioHang> sachList = adapter.getSachList();
                            if (sachList != null && !sachList.isEmpty()) {
                                // Danh sách sách không rỗng, tiếp tục xử lý
                                for (Sach_GioHang sach : sachList) {
                                    String maSach = sach.getMaSach();
                                    String soLuong = sach.getSoLuong();
                                    String donGiaBan = sach.getDonGiaBan();
                                    sachMuaList.add(new Sach_GioHang(maSach, soLuong, donGiaBan));
                                }
                            } else {
                                Log.d("Error", "Danh sách sách trống hoặc null.");
                            }
                        } else {
                            Log.d("Error", "Adapter không hợp lệ.");
                        }
                        MuaSach muaSach=new MuaSach(maKH,maGH,giamgia,phivc,pttt,tongtien,sachMuaList);
//                        Log.d("Request Data", new Gson().toJson(muaSach));
                        muaSach(muaSach);
                        ShoppingCartFragment.reset();
                    }
                    int diem= Integer.parseInt(tongtien)/1000;
                    diemtichluy.setText("Bạn đã tích lũy thêm "+diem+" điểm");
                    buyDialog.show();
                }
            }
        });
    }
    private void muaSach(MuaSach sachlist) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.muaSach(sachlist);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Mua hàng thành công", apiResponse.getMessage());
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Mua hàng thất bại", "Error: " + response.message());
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

    private void muaSach_CTS(String maKH,String maSach ,String soLuong, String donGia,String giamGia ,String phiVC, String PTTT,String tongTien) {
        // Gửi request lên server để cập nhật số lượng
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.muaSachCTS(maKH,maSach, soLuong,donGia,giamGia,phiVC,PTTT,tongTien);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Mua hàng thành công", apiResponse.getMessage());
                } else {
                    Log.d("Mua hàng thất bại", "Error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<APICapNhat> call, Throwable t) {
                Log.d("API", "Lỗi kết nối CTS"+t.getMessage());
            }
        });
    }
    private void updateTongThanhToan(String tongTien,String giamGia,String vanChuyen){
        totalPricett.setText(TinhTongTien(tongTien,vanChuyen,giamGia));
    }
    private void fetchSachMua_GioHang(String maKH,String DSSachMua) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<Sach_GioHang>> call = apiService.getSachMuaGH(maKH,DSSachMua);
        call.enqueue(new Callback<List<Sach_GioHang>>() {
            @Override
            public void onResponse(Call<List<Sach_GioHang>> call, Response<List<Sach_GioHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listSachMua_GH.clear();
                    listSachMua_GH.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Thanh Toán trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sach_GioHang>> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage(), t);
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getApplicationContext(), "Không thể gọi API Thanh Toán", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchSachMua_ChiTietSach(String SachMua) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<List<ChiTietSach>> call = apiService.getSachMuaCTS(SachMua);
        call.enqueue(new Callback<List<ChiTietSach>>() {
            @Override
            public void onResponse(Call<List<ChiTietSach>> call, Response<List<ChiTietSach>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listSachMua_CTS.clear();
                    listSachMua_CTS.addAll(response.body());
                    adapter_CTS.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "API Thanh Toán trả về lỗi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietSach>> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage(), t);
                // Xử lý lỗi khi gọi API thất bại
                Toast.makeText(getApplicationContext(), "Không thể gọi API Thanh Toán", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemClickSachMua(View view, int position, long id) {
        Sach_GioHang sach = listSachMua_GH.get(position);
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getApplicationContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", sach.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
    @Override
    public void onItemClickSachMua_CTS(View view, int position, long id) {
        ChiTietSach sach = listSachMua_CTS.get(position);
        // Khi click vào sản phẩm, mở màn hình chi tiết và truyền thông tin sản phẩm
        Intent intent = new Intent(getApplicationContext(), DetailsHotBookActivity.class);
        intent.putExtra("MaSach", sach.getMaSach()); // Truyền mã sản phẩm
        startActivity(intent);
    }
    public static String chuyenso(String so){
        return so.replace("đ", "").replace(".", "");
    }
    public static String TinhTongTien(String currency1, String currency2, String currency3) {
        // Loại bỏ ký tự "đ" và dấu phân cách nhóm (dấu chấm)
        long number1 = Long.parseLong(currency1.replace("đ", "").replace(".", ""));
        long number2 = Long.parseLong(currency2.replace("đ", "").replace(".", ""));
        long number3 = Long.parseLong(currency3.replace("đ", "").replace(".", ""));
        // Cộng các số
        long result = number1 + number2-number3;
        // Định dạng lại kết quả với dấu phân cách nhóm là dấu chấm và thêm ký tự "đ"
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.'); // Thiết lập dấu phân cách nhóm là dấu chấm
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(result) + "đ";
    }
}