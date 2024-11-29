package com.example.baocao1.Model;

public class DangNhap {
    private String status,message,MaKhachHang,TenKhachHang,MaGioHang;

    public DangNhap(String status, String message, String maKhachHang, String tenKhachHang, String maGioHang) {
        this.status = status;
        this.message = message;
        MaKhachHang = maKhachHang;
        TenKhachHang = tenKhachHang;
        MaGioHang = maGioHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getMaGioHang() {
        return MaGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        MaGioHang = maGioHang;
    }
}
