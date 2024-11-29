package com.example.baocao1.Model;

public class ChiTietSach {
    private String MaSach,TenSach,DonGiaBan,SoLuongBan,SoLuongCon,HinhAnh,MoTa,TenTacGia, SoLuong;

    public ChiTietSach(String maSach, String tenSach, String donGiaBan, String soLuongBan, String soLuongCon, String hinhAnh, String moTa, String tenTacGia, String soLuong) {
        MaSach = maSach;
        TenSach = tenSach;
        DonGiaBan = donGiaBan;
        SoLuongBan = soLuongBan;
        SoLuongCon = soLuongCon;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        TenTacGia = tenTacGia;
        SoLuong = soLuong;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
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

    public String getDonGiaBan() {
        return DonGiaBan;
    }

    public void setDonGiaBan(String donGiaBan) {
        DonGiaBan = donGiaBan;
    }

    public String getSoLuongBan() {
        return SoLuongBan;
    }

    public void setSoLuongBan(String soLuongBan) {
        SoLuongBan = soLuongBan;
    }

    public String getSoLuongCon() {
        return SoLuongCon;
    }

    public void setSoLuongCon(String soLuongCon) {
        SoLuongCon = soLuongCon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }
}
