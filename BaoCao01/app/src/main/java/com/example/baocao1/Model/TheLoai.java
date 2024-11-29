package com.example.baocao1.Model;

public class TheLoai {
    private String MaTheLoai,TenTheLoai,HinhAnh;

    public String getMaTheLoai() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        MaTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public TheLoai(String maTheLoai, String tenTheLoai, String hinhAnh) {
        MaTheLoai = maTheLoai;
        TenTheLoai = tenTheLoai;
        HinhAnh = hinhAnh;
    }
}
