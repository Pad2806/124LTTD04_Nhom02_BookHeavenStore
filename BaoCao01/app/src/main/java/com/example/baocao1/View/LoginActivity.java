package com.example.baocao1.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.DangNhap;
import com.example.baocao1.R;
import com.example.baocao1.ViewAdmin.AdminActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText email, password;
    private TextView register, forgotPass;
    public static String MaKhachHang="";
    public static String MaGioHang="";
    public static String TenKhachHang="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        register = findViewById(R.id.register);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPass = findViewById(R.id.forgotPass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);
                String username=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (!username.isEmpty() && !pass.isEmpty()) {
                    loginUser(username, pass);
                    email.setText("");
                    password.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void loginUser(String Email, String MatKhau) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<DangNhap> call = apiService.checkLogin(Email, MatKhau);
        call.enqueue(new Callback<DangNhap>() {
            @Override
            public void onResponse(Call<DangNhap> call, Response<DangNhap> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DangNhap loginResponse = response.body();
                    if ("success".equals(loginResponse.getStatus())) {
                        Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // Chuyển hướng sang trang chính
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        MaKhachHang=loginResponse.getMaKhachHang();
                        MaGioHang=loginResponse.getMaGioHang();
                        TenKhachHang=loginResponse.getTenKhachHang();
                    } else {
                        Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DangNhap> call, Throwable t) {
                Log.e("API_ERROR", "Error connecting to server", t);
                Toast.makeText(getApplicationContext(), "Không thể kết nối đến server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
