package com.example.baocao1.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Model.KhachHang;
import com.example.baocao1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private TextView updateInfo, btnCancel, btnAgree;
    private TextView tenKH, diachi, hang, sdt, email, ngaysinh, ngaydk,diem;
    private ImageView anhKH;
    private FrameLayout orderHistory, buyHistory, logoutBtn;
    Dialog dialog;
    View view;
    public static String TenKhachHang="";
    public static String DiaChi="";
//    public static String MaGioHang="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        tenKH = view.findViewById(R.id.tenKH);
        diachi = view.findViewById(R.id.diachi);
        hang = view.findViewById(R.id.hang);
        diem = view.findViewById(R.id.diem);
        sdt = view.findViewById(R.id.sdt);
        email = view.findViewById(R.id.email);
        ngaysinh = view.findViewById(R.id.ngaysinh);
        ngaydk = view.findViewById(R.id.ngaydk);
        anhKH = view.findViewById(R.id.anhKH);

        fetchThongTin(LoginActivity.MaKhachHang);

        updateInfo = view.findViewById(R.id.updateInfo);
        orderHistory = view.findViewById(R.id.orderHistory);
        buyHistory = view.findViewById(R.id.buyHistory);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.layout_dialog_logout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog.setCancelable(false);

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnAgree = dialog.findViewById(R.id.buyAgree);

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdateUserInfoActivity.class));
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OrderHistoryActivity.class));
            }
        });

        buyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BuyHistoryActivity.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Gọi lại hàm để tải lại dữ liệu
        fetchThongTin(LoginActivity.MaKhachHang);
    }
    private void fetchThongTin(String MaKhachHang) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<KhachHang> call = apiService.getThongTinKH(MaKhachHang);

        call.enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KhachHang khachHang = response.body();
                    // Hiển thị thông tin khách hàng nhận được từ API
                    String imageUrl = khachHang.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
                    Glide.with(getContext())
                            .load(imageUrl)
                            .into(anhKH);
                    TenKhachHang=khachHang.getTenKhachHang();
                    tenKH.setText(khachHang.getTenKhachHang());
                    DiaChi=khachHang.getDiaChi();
                    diachi.setText(khachHang.getDiaChi());
                    hang.setText(khachHang.getHangThanhVien());
                    diem.setText(khachHang.getDiem()+" điểm");
                    sdt.setText(khachHang.getSDT());
                    email.setText(khachHang.getEmail());
                    ngaysinh.setText(convertDateFormat(khachHang.getNgaySinh()));
                    ngaydk.setText(convertDateFormat(khachHang.getNgayDangKy()));
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                Log.e("API_ERROR", "Error connecting to server", t);
                Toast.makeText(getActivity().getApplicationContext(), "Lỗi khi gọi API", Toast.LENGTH_SHORT).show();
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
}