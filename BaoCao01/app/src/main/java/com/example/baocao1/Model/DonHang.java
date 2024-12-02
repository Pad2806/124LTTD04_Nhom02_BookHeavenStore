package com.example.baocao1.Model;

public class DonHang {
    private String MaDonHang,MaSach,HinhAnh,TenSach,SoLuong,ThanhTien,DonGiaBan,MaDanhGia,DanhGia,NoiDung,NgayDatHang,GioDatHang,NgayGiaoHang, TrangThai;

    public DonHang(String maDonHang, String maSach, String hinhAnh, String tenSach, String soLuong, String thanhTien, String maDanhGia, String donGiaBan, String danhGia, String noiDung, String ngayDatHang, String gioDatHang, String ngayGiaoHang, String trangThai) {
        MaDonHang = maDonHang;
        MaSach = maSach;
        HinhAnh = hinhAnh;
        TenSach = tenSach;
        SoLuong = soLuong;
        ThanhTien = thanhTien;
        MaDanhGia = maDanhGia;
        DonGiaBan = donGiaBan;
        DanhGia = danhGia;
        NoiDung = noiDung;
        NgayDatHang = ngayDatHang;
        GioDatHang = gioDatHang;
        NgayGiaoHang = ngayGiaoHang;
        TrangThai = trangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getNgayGiaoHang() {
        return NgayGiaoHang;
    }

    public void setNgayGiaoHang(String ngayGiaoHang) {
        NgayGiaoHang = ngayGiaoHang;
    }

    public String getNgayDatHang() {
        return NgayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        NgayDatHang = ngayDatHang;
    }

    public String getGioDatHang() {
        return GioDatHang;
    }

    public void setGioDatHang(String gioDatHang) {
        GioDatHang = gioDatHang;
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
