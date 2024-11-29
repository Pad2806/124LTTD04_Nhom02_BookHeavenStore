package com.example.baocao1.Model;

import java.util.Date;

public class KhachHang {
    private String MaKhachHang,TenKhachHang,DiaChi,Email,MatKhau,SDT,HangThanhVien,
            MaGioHang,HinhAnh,NgaySinh,NgayDangKy,Diem;

    public KhachHang(String maKhachHang, String tenKhachHang, String diaChi, String email, String matKhau, String SDT, String hangThanhVien, String maGioHang, String hinhAnh, String ngaySinh, String ngayDangKy, String diem) {
        MaKhachHang = maKhachHang;
        TenKhachHang = tenKhachHang;
        DiaChi = diaChi;
        Email = email;
        MatKhau = matKhau;
        this.SDT = SDT;
        HangThanhVien = hangThanhVien;
        MaGioHang = maGioHang;
        HinhAnh = hinhAnh;
        NgaySinh = ngaySinh;
        NgayDangKy = ngayDangKy;
        Diem = diem;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public void setNgayDangKy(String ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHangThanhVien() {
        return HangThanhVien;
    }

    public void setHangThanhVien(String hangThanhVien) {
        HangThanhVien = hangThanhVien;
    }

    public String getMaGioHang() {
        return MaGioHang;
    }

    public void setMaGioHang(String maGioHang) {
        MaGioHang = maGioHang;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKi(String ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }
}
