package com.example.baocao1.Model;

import java.util.List;

public class MuaSach {
    private String MaKhachHang;
    private String MaGioHang;
    private String GiamGia;
    private String PhiVanChuyen;
    private String MaPTTT;
    private String TongTien;
    private List<Sach_GioHang> sachMuaList;

    public MuaSach(String maKhachHang, String maGioHang, String giamGia, String phiVanChuyen, String maPTTT, String tongTien, List<Sach_GioHang> sachMuaList) {
        MaKhachHang = maKhachHang;
        MaGioHang = maGioHang;
        GiamGia = giamGia;
        PhiVanChuyen = phiVanChuyen;
        MaPTTT = maPTTT;
        TongTien = tongTien;
        this.sachMuaList = sachMuaList;
    }
}