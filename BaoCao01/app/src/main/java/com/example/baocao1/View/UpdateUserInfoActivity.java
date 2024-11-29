package com.example.baocao1.View;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.KhachHang;
import com.example.baocao1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserInfoActivity extends AppCompatActivity {
    private ImageView icon_back,hinhanh;
    private TextView updateInfo;
    private EditText ten,diachi,sdt,email,matkhau,ngaysinh;
    Dialog dialog;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cntt_taikhoan_user);

        icon_back = findViewById(R.id.icon_back);
        updateInfo = findViewById(R.id.updateInfo);
        hinhanh = findViewById(R.id.hinhanhcntt);
        ten = findViewById(R.id.tencntt);
        diachi = findViewById(R.id.diachicntt);
        sdt = findViewById(R.id.sdtcntt);
        email = findViewById(R.id.emailcntt);
        matkhau = findViewById(R.id.matkhaucntt);
        ngaysinh = findViewById(R.id.ngaysinhcntt);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_updateuserinfo);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_ordersuccess_bg));
        dialog.setCancelable(false);

        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fetchThongTinUpdate(LoginActivity.MaKhachHang);
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
                    Call<APICapNhat> call = apiService.postKH(
                            LoginActivity.MaKhachHang,
                            ten.getText().toString(),
                            diachi.getText().toString(),
                            sdt.getText().toString(),
                            email.getText().toString(),
                            matkhau.getText().toString(),
                            convertDatetoAPI(ngaysinh.getText().toString())
                    );
                    call.enqueue(new Callback<APICapNhat>() {
                         @Override
                         public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
//                             dialog.dismiss(); // Tắt dialog sau khi cập nhật
                             if (response.isSuccessful() && response.body() != null) {
                                 APICapNhat apiResponse = response.body();
                                 Log.d("API Cập Nhật", apiResponse.getMessage());
                                 dialog.show();
                                 new Handler().postDelayed(new Runnable() {
                                     @Override
                                     public void run() {
                                         dialog.dismiss();
                                         finish();
                                     }
                                 }, 1500);
                             } else {
                                 Log.e("API Cập Nhật", "Error: " + response.message());
                                 Toast.makeText(UpdateUserInfoActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                             }
                         }
                         @Override
                         public void onFailure(Call<APICapNhat> call, Throwable t) {
                             dialog.dismiss(); // Tắt dialog nếu có lỗi xảy ra
                             Log.e("API Cập Nhật", t.getMessage());
                             Toast.makeText(UpdateUserInfoActivity.this, "Lỗi khi gọi API Cập Nhật", Toast.LENGTH_SHORT).show();
                         }
                    });
                }
            }
        });

    }
    private void fetchThongTinUpdate(String MaKhachHang) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<KhachHang> call = apiService.getThongTinKH(MaKhachHang);

        call.enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KhachHang khachHang = response.body();
                    // Hiển thị thông tin khách hàng nhận được từ API
                    String imageUrl = khachHang.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
                    Glide.with(getApplicationContext())
                            .load(imageUrl)
                            .into(hinhanh);
                    ten.setText(khachHang.getTenKhachHang());
                    diachi.setText(khachHang.getDiaChi());
                    matkhau.setText(khachHang.getMatKhau());
                    sdt.setText(khachHang.getSDT());
                    email.setText(khachHang.getEmail());
                    ngaysinh.setText(convertDateFormat(khachHang.getNgaySinh()));
//                    MaGioHang=khachHang.getMaGioHang();
//                    Toast.makeText(getActivity().getApplicationContext(), MaGioHang, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                Log.e("API_ERROR", "Error connecting to server", t);
                Toast.makeText(getApplicationContext(), "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private String convertDateFormat(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return "01-01-2000";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                return outputFormat.format(date);
            } else {
                return "01-01-2001";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "01-01-2002";
        }
    }
    private String convertDatetoAPI(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return "2000-01-01";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date date = inputFormat.parse(dateStr);
            if (date != null) {
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                return outputFormat.format(date);
            } else {
                return "2000-01-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "2000-01-01";
        }
    }
    private boolean validateFields() {
        if (ten.getText().toString().isEmpty()) {
            ten.setError("Tên không được để trống");
            return false;
        }
        if (diachi.getText().toString().isEmpty()) {
            diachi.setError("Địa chỉ không được để trống");
            return false;
        }
        if (sdt.getText().toString().isEmpty()) {
            sdt.setError("Số điện thoại không được để trống");
            return false;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Email không được để trống");
            return false;
        }
        if (matkhau.getText().toString().isEmpty()) {
            matkhau.setError("Mật khẩu không được để trống");
            return false;
        }
        if (ngaysinh.getText().toString().isEmpty()) {
            ngaysinh.setError("Ngày sinh không được để trống");
            return false;
        }
        return true;
    }
}