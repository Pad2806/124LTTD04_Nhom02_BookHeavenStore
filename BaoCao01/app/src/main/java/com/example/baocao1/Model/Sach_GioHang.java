package com.example.baocao1.Model;

public class Sach_GioHang {
    private String MaSach, TenSach, SoLuong,DonGiaBan,ThanhTien,HinhAnh;

    public Sach_GioHang(String maSach, String tenSach, String soLuong, String donGiaBan, String thanhTien, String hinhAnh) {
        MaSach = maSach;
        TenSach = tenSach;
        SoLuong = soLuong;
        DonGiaBan = donGiaBan;
        ThanhTien = thanhTien;
        HinhAnh = hinhAnh;

    }

    public Sach_GioHang(String maSach, String soLuong, String donGiaBan) {
        MaSach = maSach;
        SoLuong = soLuong;
        DonGiaBan = donGiaBan;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getDonGiaBan() {
        return DonGiaBan;
    }

    public void setDonGiaBan(String donGiaBan) {
        DonGiaBan = donGiaBan;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
