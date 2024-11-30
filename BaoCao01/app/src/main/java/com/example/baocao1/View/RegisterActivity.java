package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.DangNhap;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn, agreeBtn;
    private TextView login;
    private EditText email,sdt,matkhau,xacnhanmatkhau;
    Dialog dialog;
    private Boolean test=true;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangki);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerBtn);
        email = findViewById(R.id.email);
        sdt = findViewById(R.id.sdt);
        matkhau = findViewById(R.id.matkhau);
        xacnhanmatkhau = findViewById(R.id.xacnhanmatkhau);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_register);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogsuccessed_bg));
        dialog.setCancelable(false);

        agreeBtn = dialog.findViewById(R.id.btn_agree);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent(RegisterActivity.this, LoginActivity.class)});
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kiemtrataikhoan(email.getText().toString(),matkhau.getText().toString(),email,sdt);
                if (validateInputs(email, sdt, matkhau, xacnhanmatkhau) && test) {
                    dialog.show();
                    themKhachHang(email.getText().toString(),sdt.getText().toString(),matkhau.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }
    private void themKhachHang(String Email,String SDT, String MatKhau) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.themKhachHang(Email,SDT,MatKhau);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Thêm khách hàng thành công", apiResponse.getMessage());
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Thêm khách hàng thất bại", "Error: " + response.message());
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
    public void kiemtrataikhoan(String Email, String MatKhau,EditText email,EditText sdt) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.checkDangKi(Email, MatKhau);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat Response = response.body();
                    String error=Response.getMessage();
                    if(error.equals("Email")){
                        email.setError("Email đã tồn tại");
                        test=false;
                    }
                    if(error.equals("SDT")){
                        sdt.setError("Số điện thoại đã tồn tại");
                        test=false;
                    }
                    if(error.equals("EmailSDT")){
                        email.setError("Email đã tồn tại");
                        sdt.setError("Số điện thoại đã tồn tại");
                        test=false;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<APICapNhat> call, Throwable t) {
                Log.e("API_ERROR", "Error connecting to server", t);
                Toast.makeText(getApplicationContext(), "Không thể kết nối đến server", Toast.LENGTH_SHORT).show();
                test=false;
            }
        });
    }
    public boolean validateInputs(EditText edtEmail, EditText edtPhone, EditText edtPassword, EditText edtConfirmPassword) {
        boolean isValid = true;
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        // Kiểm tra email
        if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            edtEmail.setError("Email không hợp lệ");
            isValid = false;
        }
        // Kiểm tra số điện thoại
        if (phone.isEmpty() || !phone.matches("\\d{10,11}")) {
            edtPhone.setError("Số điện thoại không hợp lệ");
            isValid = false;
        }
        // Kiểm tra mật khẩu
        if (password.isEmpty() || password.length() < 6) {
            edtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            isValid = false;
        }
        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            edtConfirmPassword.setError("Mật khẩu không khớp");
            isValid = false;
        }
        return isValid;
    }
}
