package com.example.baocao1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class DetailsHotBookActivity extends AppCompatActivity {
    private ImageView icon_back, icon_cancel,pdfImageView,pdfbook;
    private TextView plus, minus, numOrder, btnBuyBook, btnOrderBook, btnadd,btnReview, price;
    private int count = 1;
    Button btnClosePdf,btnPrevPage, btnNextPage;
    Dialog dialog, dialog1;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private int currentPageIndex = 7;
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsach_user);

        icon_back = findViewById(R.id.icon_back);
        btnBuyBook = findViewById(R.id.btnBuyBook);
        btnOrderBook = findViewById(R.id.btnOrderBook);
        pdfImageView=findViewById(R.id.pdfImageView);
        pdfbook=findViewById(R.id.pdfbook);
        btnClosePdf=findViewById(R.id.btnClosePdf);
        btnPrevPage=findViewById(R.id.btnPrevPage);
        btnNextPage=findViewById(R.id.btnNextPage);

        pdfbook.setOnClickListener(new View.OnClickListener() {
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
        price = dialog.findViewById(R.id.price);
        plus = dialog.findViewById(R.id.plus);
        minus = dialog.findViewById(R.id.minus);
        numOrder = dialog.findViewById(R.id.numOrder);
        btnadd = dialog.findViewById(R.id.btn_add);
        icon_cancel = dialog.findViewById(R.id.icon_cancel);


        icon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsHotBookActivity.this, HomeActivity.class));
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
                startActivity(new Intent(DetailsHotBookActivity.this, PaymentActivity.class));
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
                    price.setText(String.valueOf(100000 * count) + " đ");
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
                    price.setText(String.valueOf(100000 * count) + " đ");
                }else {
                    count = 1;
                    numOrder.setText(String.valueOf(count));
                    price.setText("100000 đ");
                }
                UpdateOrderBtn();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog1.dismiss();
                        startActivity(new Intent(DetailsHotBookActivity.this, DetailsHotBookActivity.class));
                    }
                }, 1000);
            }
        });
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
