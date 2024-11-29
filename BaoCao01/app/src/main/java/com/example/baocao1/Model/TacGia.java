package com.example.baocao1.Model;

public class TacGia {
    private String MaTacGia,TenTacGia,HinhAnh;
    private String SoLuongTP;

    public String getMaTacGia() {
        return MaTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        MaTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getSoLuongTP() {
        return SoLuongTP;
    }

    public void setSoLuongTP(String soLuongTP) {
        SoLuongTP = soLuongTP;
    }

    public TacGia(String maTacGia, String tenTacGia, String hinhAnh, String soLuongTP) {
        MaTacGia = maTacGia;
        TenTacGia = tenTacGia;
        HinhAnh = hinhAnh;
        SoLuongTP = soLuongTP;
    }
}
