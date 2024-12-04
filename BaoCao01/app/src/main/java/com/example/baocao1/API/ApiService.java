package com.example.baocao1.API;

import com.example.baocao1.Model.DangNhap;
import com.example.baocao1.Model.DanhGiaKH;
import com.example.baocao1.Model.DonHang;
import com.example.baocao1.Model.KhachHang;
import com.example.baocao1.Model.ChiTietSach;
import com.example.baocao1.Model.MuaSach;
import com.example.baocao1.Model.Sach_DanhGia;
import com.example.baocao1.Model.Sach_GioHang;
import com.example.baocao1.Model.TacGia;
import com.example.baocao1.Model.TheLoai;
import com.example.baocao1.Model.ThongBao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("getNoiBat.php")
    Call<List<ChiTietSach>> getSPNoiBat();

    @GET("getTheLoai.php")
    Call<List<TheLoai>> getTheLoai();

    @GET("getTacGia.php")
    Call<List<TacGia>> getTacGia();

    @FormUrlEncoded
    @POST("checkLogin.php")
    Call<DangNhap> checkLogin(@Field("Email") String Email, @Field("MatKhau") String MatKhau);

//    @POST("getThongTinUser.php")
//    Call<KhachHang> getThongTinKH(@Query("MaKhachHang") String MaKhachHang);
//
//    @POST("getThongTinSach.php")
//    Call<ChiTietSach> getThongTinSach(@Query("MaSach") String MaSach);
//
//    @POST("getGioHang.php")
//    Call<List<Sach_GioHang>> getGioHang(@Query("MaGioHang") String MaGioHang);
//
//    @POST("getSach_TheLoai.php")
//    Call<List<ChiTietSach>> getSach_TheLoai(@Query("MaTheLoai") String MaTheLoai);
//
//    @POST("getSach_TacGia.php")
//    Call<List<ChiTietSach>> getSach_TacGia(@Query("MaTacGia") String MaTacGia);
//
//    @POST("getDonNhan.php")
//    Call<List<DonHang>> getDonNhan(@Query("MaKhachHang") String MaKhachHang);
//
//    @POST("getDonMua.php")
//    Call<List<DonHang>> getDonMua(@Query("MaKhachHang") String MaKhachHang);
    @GET("getThongTinUser.php")
    Call<KhachHang> getThongTinKH(@Query("MaKhachHang") String MaKhachHang);

    @GET("getThongTinSach.php")
    Call<ChiTietSach> getThongTinSach(@Query("MaSach") String MaSach);

    @GET("getGioHang.php")
    Call<List<Sach_GioHang>> getGioHang(@Query("MaGioHang") String MaGioHang);

    @GET("getSach_TheLoai.php")
    Call<List<ChiTietSach>> getSach_TheLoai(@Query("MaTheLoai") String MaTheLoai);

    @GET("getSach_TacGia.php")
    Call<List<ChiTietSach>> getSach_TacGia(@Query("MaTacGia") String MaTacGia);

    @GET("getDonNhan.php")
    Call<List<DonHang>> getDonNhan(@Query("MaKhachHang") String MaKhachHang);

    @GET("getDonMua.php")
    Call<List<DonHang>> getDonMua(@Query("MaKhachHang") String MaKhachHang);

    @FormUrlEncoded
    @POST("postTTKH.php")
    Call<APICapNhat> postKH(@Field("MaKhachHang") String makhachhang,
                            @Field("TenKhachHang") String tenkhachhang,
                            @Field("DiaChi") String diachi,
                            @Field("SDT") String sdt,
                            @Field("Email") String email,
                            @Field("MatKhau") String matkhau,
                            @Field("NgaySinh") String ngaysinh);

    @GET("getSachMua_CTS.php")
    Call<List<ChiTietSach>> getSachMuaCTS(@Query("MaSach") String MaSach);

    @GET("getSachMua_GioHang.php")
    Call<List<Sach_GioHang>> getSachMuaGH(@Query("MaKhachHang") String makh,@Query("DSSachMua") String list);

    @GET("updateSach_GioHang.php")
    Call<APICapNhat> updateSoLuong(@Query("MaGioHang") String MaGioHang,@Query("MaSach") String MaSach,@Query("SoLuong") String SoLuong);

    @GET("muaSachCTS.php")
    Call<APICapNhat> muaSachCTS(@Query("MaKhachHang") String MaKhachHang,
                                @Query("MaSach") String MaSach,
                                @Query("SoLuong") String SoLuong,
                                @Query("DonGia") String DonGia,
                                @Query("GiamGia") String GiamGia,
                                @Query("PhiVanChuyen") String PhiVanChuyen,
                                @Query("PTTT") String PTTT,
                                @Query("TongTien") String TongTien);

    @POST("muaSach.php")
    Call<APICapNhat> muaSach(@Body MuaSach sachmua);

    @GET("xoaSach_GioHang.php")
    Call<APICapNhat> xoaSach_GioHang(@Query("DSSachMua") String list);

    @GET("themSach_GioHang.php")
    Call<APICapNhat> themSach_GioHang(@Query("MaGioHang") String MaGioHang,@Query("MaSach") String MaSach,@Query("SoLuong") String SoLuong,@Query("DonGia") String DonGia);

    @GET("checkDangKi.php")
    Call<APICapNhat> checkDangKi(@Query("Email") String Email,@Query("SDT") String SDT);

    @GET("themKhachHang.php")
    Call<APICapNhat> themKhachHang(@Query("Email") String Email,@Query("SDT") String SDT,@Query("MatKhau") String MatKhau);

    @GET("checkTaiKhoan.php")
    Call<APICapNhat> checkTaiKhoan(@Query("Email") String Email,@Query("SDT") String SDT);

    @GET("updateTaiKhoan.php")
    Call<APICapNhat> updateTaiKhoan(@Query("Email") String Email,@Query("SDT") String SDT,@Query("MatKhau") String MatKhau);

    @GET("getDanhGiaSach.php")
    Call<Sach_DanhGia> getDanhGiaSach(@Query("MaDanhGia") String MaDanhGia);

    @GET("DanhGia.php")
    Call<APICapNhat> DanhGia(@Query("MaDanhGia") String MaDanhGia,@Query("DanhGia") String DanhGia,@Query("NoiDung") String NoiDung);

    @GET("huyDonHang.php")
    Call<APICapNhat> huyDonHang(@Query("MaKhachHang") String MaKhachHang,@Query("MaDonHang") String MaDonHang);

    @GET("getDanhGia.php")
    Call<List<DanhGiaKH>> getDanhGia(@Query("MaSach") String MaSach);

    @GET("getThongBao.php")
    Call<List<ThongBao>> getThongBao(@Query("MaKhachHang") String MaKhachHang);
}