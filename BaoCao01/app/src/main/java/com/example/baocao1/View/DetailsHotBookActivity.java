package com.example.baocao1.View;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.baocao1.API.APICapNhat;
import com.example.baocao1.API.ApiController;
import com.example.baocao1.API.ApiService;
import com.example.baocao1.Adapter.ShoppingCartAdapter;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsHotBookActivity extends AppCompatActivity {
    private String maSach;
    private ImageView icon_back, icon_cancel,pdfImageView,anhSach;
    private TextView plus, minus, numOrder, btnBuyBook, btnOrderBook, btnadd;
    private TextView tieude,tenSach,giaSach,tacgia,luotban,tinhtrang,mota;
    private int count = 1;
    Button btnClosePdf,btnPrevPage, btnNextPage;
    Dialog dialog, dialog1;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private int currentPageIndex = 7;
    private ChiTietSach sach;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsach_user);

        icon_back = findViewById(R.id.icon_back);
        btnBuyBook = findViewById(R.id.btnBuyBook);
        btnOrderBook = findViewById(R.id.btnOrderBook);
        pdfImageView=findViewById(R.id.pdfImageView);

        btnClosePdf=findViewById(R.id.btnClosePdf);
        btnPrevPage=findViewById(R.id.btnPrevPage);
        btnNextPage=findViewById(R.id.btnNextPage);

        tieude=findViewById(R.id.tieude);
        anhSach=findViewById(R.id.anhSach);
        tenSach=findViewById(R.id.tenSach);
        giaSach=findViewById(R.id.giaSach);
        tacgia=findViewById(R.id.tacgia);
        luotban=findViewById(R.id.luotban);
        tinhtrang=findViewById(R.id.tinhtrang);
        mota=findViewById(R.id.mota);

        anhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPdf(); // Mở tệp PDF
                findViewById(R.id.linearpdf).setVisibility(View.VISIBLE);
                findViewById(R.id.scrthongtin).setVisibility(View.GONE);
            }
        });
        btnClosePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePdf();
                // Ẩn ScrollView và nút đóng
                findViewById(R.id.linearpdf).setVisibility(View.GONE);
                findViewById(R.id.scrthongtin).setVisibility(View.VISIBLE);
            }
        });
        // Gán sự kiện click cho nút trang trước
        btnPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPageIndex > 7) {
                    currentPageIndex--;
                    showPage(currentPageIndex);
                }
            }
        });

        // Gán sự kiện click cho nút trang tiếp theo
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPageIndex < pdfRenderer.getPageCount() - 1) {
                    currentPageIndex++;
                    showPage(currentPageIndex);
                }
            }
        });

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_shoppingcart);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogorder_bg));
        dialog.setCancelable(false);

        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.layout_dialog_addshoppingcartsuccessed);
        Objects.requireNonNull(dialog1.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialog_ordersuccess_bg);
        dialog1.setCancelable(false);
        plus = dialog.findViewById(R.id.plus);
        minus = dialog.findViewById(R.id.minus);
        numOrder = dialog.findViewById(R.id.numOrder);
        btnadd = dialog.findViewById(R.id.btn_add);
        icon_cancel = dialog.findViewById(R.id.icon_cancel);

        // Nhận mã sách từ Intent
        maSach = getIntent().getStringExtra("MaSach");
        if (maSach != null) {
            // Gọi API để lấy chi tiết sản phẩm từ mã sách
            fetchChiTietSach(maSach);
        } else {
            // Xử lý trường hợp không có mã sách
            Toast.makeText(this, "Không tìm thấy sản phẩm để hiển thị", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có mã sản phẩm hợp lệ
        }
        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(DetailsHotBookActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnOrderBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("MaSach", maSach); // Truyền mã sản phẩm
                intent.putExtra("From", "DetailsHotBookActivity");
                startActivity(intent);
//                startActivity(new Intent(DetailsHotBookActivity.this, PaymentActivity.class));
            }
        });
        icon_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count < 10){
                    count++;
                    numOrder.setText(String.valueOf(count));
                }
                UpdateOrderBtn();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(count > 1){
                    count--;
                    numOrder.setText(String.valueOf(count));
                }else {
                    count = 1;
                    numOrder.setText(String.valueOf(count));
                }
                UpdateOrderBtn();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                themSach(LoginActivity.MaGioHang,maSach,numOrder.getText().toString(),giaSach.getText().toString().replace(".",""));
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog1.dismiss();
                        ShoppingCartAdapter.resetSelectedBooks();
                        ShoppingCartFragment.reset();
//                        startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
//                        startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
                    }
                }, 1000);
            }
        });

    }
    private void themSach(String maGioHang,String maSach,String soLuong,String donGia) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<APICapNhat> call = apiService.themSach_GioHang(maGioHang,maSach,soLuong,donGia);
        call.enqueue(new Callback<APICapNhat>() {
            @Override
            public void onResponse(Call<APICapNhat> call, Response<APICapNhat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICapNhat apiResponse = response.body();
                    Log.d("Thêm sách thành công", apiResponse.getMessage());
//                    Toast.makeText(getApplicationContext(), "onResponse"+apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Thêm sách thất bại", "Error: " + response.message());
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
    private void fetchChiTietSach(String maSach) {
        ApiService apiService = ApiController.getRetrofitInstance().create(ApiService.class);
        Call<ChiTietSach> call = apiService.getThongTinSach(maSach);
        call.enqueue(new Callback<ChiTietSach>() {
            @Override
            public void onResponse(Call<ChiTietSach> call, Response<ChiTietSach> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sach = response.body();
                    String imageUrl = sach.getHinhAnh().replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
                    Glide.with(DetailsHotBookActivity.this)
                            .load(imageUrl)
                            .into(anhSach);
                    tieude.setText(sach.getTenSach());
                    tenSach.setText(sach.getTenSach());
                    giaSach.setText(formatCurrency(Long.parseLong(sach.getDonGiaBan())));
                    tacgia.setText(sach.getTenTacGia());
                    luotban.setText(sach.getSoLuongBan());
                    tinhtrang.setText("Còn("+sach.getSoLuongCon()+")");
                    mota.setText(sach.getMoTa());
                } else {
                    Toast.makeText(DetailsHotBookActivity.this, "Không thể tải chi tiết sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChiTietSach> call, Throwable t) {
                Toast.makeText(DetailsHotBookActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static String formatCurrency(long number) {
        // Tạo đối tượng DecimalFormatSymbols để tùy chỉnh ký tự phân cách
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.'); // Thiết lập dấu phân cách nhóm là dấu chấm
        // Định dạng tiền tệ với dấu chấm phân tách các phần nghìn
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        // Trả về chuỗi đã định dạng
        return formatter.format(number) + "đ";
    }
    private void UpdateOrderBtn() {
        numOrder.setText(String.valueOf(count));

        if (count <= 1) {
            minus.setAlpha(0.5f);
            minus.setEnabled(false);
        } else {
            minus.setAlpha(1.0f);
            minus.setEnabled(true);
        }

        if (count >= 10) {
            plus.setAlpha(0.5f);
            plus.setEnabled(false);
        } else {
            plus.setAlpha(1.0f);
            plus.setEnabled(true);
        }
    }

    private void showPdf() {
        try {
            // Mở tệp PDF từ thư mục assets
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("SachDocThu.pdf"); // Đường dẫn đến tệp PDF trong assets

            // Lưu tệp PDF vào bộ nhớ tạm
            File tempFile = new File(getCacheDir(), "temp.pdf");
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            // Mở tệp PDF từ bộ nhớ tạm
            openRenderer(tempFile);
            showPage(currentPageIndex); // Hiển thị trang đầu tiên
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error opening PDF file!", Toast.LENGTH_SHORT).show();
        }
    }
    private void closePdf() {
        // Giải phóng tài nguyên PDF
        if (currentPage != null) {
            currentPage.close();
            currentPage = null;
        }
        if (pdfRenderer != null) {
            pdfRenderer.close();
            pdfRenderer = null;
        }
        currentPageIndex=7;
    }
    private void openRenderer(File file) {
        try {
            ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            pdfRenderer = new PdfRenderer(fileDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updatePageButtons() {
        btnPrevPage.setEnabled(currentPageIndex > 7);
        btnNextPage.setEnabled(currentPageIndex < 20);
    }
    private void showPage(int index) {
        if (index < 7 || index >= pdfRenderer.getPageCount()) {
            return;
        }

        if (currentPage != null) {
            currentPage.close();
        }

        currentPage = pdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(), Bitmap.Config.ARGB_8888);
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        pdfImageView.setImageBitmap(bitmap);
        updatePageButtons();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (currentPage != null) {
            currentPage.close();
        }
        if (pdfRenderer != null) {
            pdfRenderer.close();
        }
    }
}
