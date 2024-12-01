<?php
include("connect.php");
// Lấy dữ liệu từ GET request
$MaKhachHang = $_GET['MaKhachHang'];
$MaSach = $_GET['MaSach'];
$SoLuong = $_GET['SoLuong'];
$DonGia = $_GET['DonGia'];
$GiamGia = $_GET['GiamGia'];
$PhiVanChuyen = $_GET['PhiVanChuyen'];
$PTTT = $_GET['PTTT'];
$TongTien = $_GET['TongTien'];
// Hàm lấy mã đơn hàng mới nhất
$sql = "SELECT taoMaDonHangmoi() AS ma_don_hang";
$result = $conn->query($sql);

if ($result && $result->num_rows > 0) {
    // Lấy kết quả
    $row = $result->fetch_assoc();
    $MaDonHang =$row['ma_don_hang'];
} else {
    echo "Không có kết quả hoặc lỗi truy vấn.";
}
$sqldh = "INSERT INTO DonHang(MaDonHang, MaKhachHang, TongTien, GiamGia, PhiVanChuyen, MaPTTT, NgayDatHang, GioDatHang,TrangThai) 
          VALUES(?, ?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_TIME,'Chờ xác nhận')";
$stmt = $conn->prepare($sqldh);
$stmt->bind_param("ssssss", $MaDonHang, $MaKhachHang, $TongTien, $GiamGia, $PhiVanChuyen, $PTTT);
$executeResult = $stmt->execute(); // Chạy câu lệnh SQL
header('Content-Type: application/json; charset=UTF-8');
if ($executeResult) {
    // Cập nhật vào bảng ChiTietDonHang
    $sqlctdh = "INSERT INTO ChiTietDonHang(MaDonHang, MaSach, SoLuong, DonGia) 
                VALUES(?, ?, ?, ?)";
    $stmtCT = $conn->prepare($sqlctdh);
    $stmtCT->bind_param("ssss", $MaDonHang, $MaSach, $SoLuong, $DonGia);
    $executeResultCT = $stmtCT->execute();

    if ($executeResultCT) {
        echo json_encode(array("message" => "Mua hàng thành công.",JSON_UNESCAPED_UNICODE));
    } else {
        echo json_encode(array("message" => "Mua hàng thất bại.". $stmt->error,JSON_UNESCAPED_UNICODE));
    }
} else {
    echo json_encode(array("message" => "Không thể cập nhập bảng.". $stmt->error,JSON_UNESCAPED_UNICODE));
}
$stmtdiem = $conn->prepare("CALL CongDiem(?, ?)");
$stmtdiem->bind_param("sd", $MaKhachHang, $TongTien);
$stmtdiem->execute();
$stmtdiem->close();
$stmt->close();
$stmtCT->close();
$conn->close();
?>