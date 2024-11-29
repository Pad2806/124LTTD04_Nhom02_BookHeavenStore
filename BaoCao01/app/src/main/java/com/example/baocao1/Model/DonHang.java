package com.example.baocao1.Model;

public class DonHang {
    private String MaDonHang,MaSach,HinhAnh,TenSach,SoLuong,ThanhTien,DonGiaBan,MaDanhGia,DanhGia,NoiDung;

    public DonHang(String maDonHang, String maSach, String hinhAnh, String tenSach, String soLuong, String thanhTien, String donGiaBan, String maDanhGia, String danhGia, String noiDung) {
        MaDonHang = maDonHang;
        MaSach = maSach;
        HinhAnh = hinhAnh;
        TenSach = tenSach;
        SoLuong = soLuong;
        ThanhTien = thanhTien;
        DonGiaBan = donGiaBan;
        MaDanhGia = maDanhGia;
        DanhGia = danhGia;
        NoiDung = noiDung;
    }

    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        MaDonHang = maDonHang;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
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

    public String getMaDanhGia() {
        return MaDanhGia;
    }

    public void setMaDanhGia(String maDanhGia) {
        MaDanhGia = maDanhGia;
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
