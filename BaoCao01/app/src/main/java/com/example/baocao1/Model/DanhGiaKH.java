package com.example.baocao1.Model;

public class DanhGiaKH {
    private String TenKhachHang,DanhGia,NoiDung;

    public DanhGiaKH(String tenKhachHang, String danhGia, String noiDung) {
        TenKhachHang = tenKhachHang;
        DanhGia = danhGia;
        NoiDung = noiDung;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String danhGia) {
        DanhGia = danhGia;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
