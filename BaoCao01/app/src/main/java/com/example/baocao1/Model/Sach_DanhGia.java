package com.example.baocao1.Model;

public class Sach_DanhGia {
    private String MaDanhGia, DanhGia, NoiDung, TenTacGia, TenSach, HinhAnh;

    public Sach_DanhGia(String maDanhGia, String danhGia, String noiDung, String tenTacGia, String tenSach, String hinhAnh) {
        MaDanhGia = maDanhGia;
        DanhGia = danhGia;
        NoiDung = noiDung;
        TenTacGia = tenTacGia;
        TenSach = tenSach;
        HinhAnh = hinhAnh;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String TenTacGia) {
        TenTacGia = TenTacGia;
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
