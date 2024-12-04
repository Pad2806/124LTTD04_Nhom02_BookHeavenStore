package com.example.baocao1.Model;

public class ThongBao {
    private String NgayTB, GioTB, NoiDungTB,MaThongBao,MaKhachHang;

    public ThongBao(String ngayTB, String gioTB, String noiDungTB) {
        NgayTB = ngayTB;
        GioTB = gioTB;
        NoiDungTB = noiDungTB;
    }

    public String getNgayTB() {
        return NgayTB;
    }

    public void setNgayTB(String ngayTB) {
        NgayTB = ngayTB;
    }

    public String getGioTB() {
        return GioTB;
    }

    public void setGioTB(String gioTB) {
        GioTB = gioTB;
    }

    public String getNoiDungTB() {
        return NoiDungTB;
    }

    public void setNoiDungTB(String noiDungTB) {
        NoiDungTB = noiDungTB;
    }
}
