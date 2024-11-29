//package com.example.baocao1.Model;
//
//import java.util.ArrayList;
//
//public class DSSachMua {
//    private ArrayList<Sach_GioHang> sachMua = new ArrayList<>();
//
//    public void themSach(Sach_GioHang sachGioHang) {
//        // Kiểm tra nếu sản phẩm đã tồn tại, tăng số lượng nếu cần
//        for (Sach_GioHang sach : sachMua) {
//            if (sach.getMaSach().equals(sachGioHang.getMaSach())) {
//                sach.setSoLuong(sach.getSoLuong() + sachGioHang.getSoLuong());
//                return;
//            }else{
//                sach.setSoLuong("1");
//            }
//        }
//        sachMua.add(sachGioHang);
//    }
//
//    public ArrayList<Sach_GioHang> getSach() {
//        return sachMua;
//    }
//
//    public void xoaSach() {
//        sachMua.clear();
//    }
//}
