<?php
include("connect.php");
header('Content-Type: application/json; charset=UTF-8');
// Lấy dữ liệu từ body của request
$data = json_decode(file_get_contents("php://input"), true);
// $jsonData = '{"GiamGia":"15000","MaGioHang":"GH00000001","MaKhachHang":"KH00000001","MaPTTT":"TTTTuyen","PhiVanChuyen":"25000","TongTien":"520000","sachMuaList":[{"DonGiaBan":"85000","MaSach":"BOOK000002","SoLuong":"6"}]}';
// $data = json_decode($jsonData, true);
// if (!$data) {
//     echo json_encode(array("message" => "Dữ liệu không hợp lệ hoặc không có dữ liệu gửi lên.", JSON_UNESCAPED_UNICODE));
//     exit;
// }

// Gán dữ liệu từ request
$MaKhachHang = $data['MaKhachHang'];
$MaGioHang = $data['MaGioHang'];
$GiamGia = $data['GiamGia'];
$PhiVanChuyen = $data['PhiVanChuyen'];
$PTTT = $data['MaPTTT'];
$TongTien = $data['TongTien'];
$sachMuaList = $data['sachMuaList'];

// Kiểm tra dữ liệu đầu vào
if (empty($MaKhachHang) || empty($MaGioHang) || empty($TongTien) || empty($GiamGia) || empty($PhiVanChuyen) || empty($PTTT) || empty($sachMuaList)) {
    echo json_encode(array("message" => "Dữ liệu không đầy đủ.", JSON_UNESCAPED_UNICODE));
    exit;
}
// Tạo mã đơn hàng mới
$sql = "SELECT taoMaDonHangmoi() AS ma_don_hang";
$result = $conn->query($sql);

if ($result && $result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $MaDonHang = $row['ma_don_hang'];
} else {
    echo json_encode(array("message" => "Không thể tạo mã đơn hàng mới.", JSON_UNESCAPED_UNICODE));
    exit;
}

// Thêm đơn hàng vào bảng DonHang
if($PTTT=='TTTTuyen'){
    $sqldh = "INSERT INTO DonHang(MaDonHang, MaKhachHang, TenDonHang, TongTien, GiamGia, PhiVanChuyen, MaPTTT, NgayDatHang, GioDatHang,TrangThai, NgayThanhToan) 
          VALUES(?, ?, CONCAT('Đơn hàng ', ?), ?, ?, ?, ?, CURRENT_DATE, CURRENT_TIME,'Chờ xác nhận', CURRENT_DATE)";
}else{
    $sqldh = "INSERT INTO DonHang(MaDonHang, MaKhachHang, TenDonHang, TongTien, GiamGia, PhiVanChuyen, MaPTTT, NgayDatHang, GioDatHang,TrangThai) 
          VALUES(?, ?, CONCAT('Đơn hàng ', ?), ?, ?, ?, ?, CURRENT_DATE, CURRENT_TIME,'Chờ xác nhận')";
}

$stmt = $conn->prepare($sqldh);
$stmt->bind_param("sssssss", $MaDonHang, $MaKhachHang, $MaDonHang,  $TongTien, $GiamGia, $PhiVanChuyen, $PTTT);
if (!$stmt->execute()) {
    echo json_encode(array("message" => "Không thể thêm đơn hàng.", JSON_UNESCAPED_UNICODE));
    exit;
}
foreach ($sachMuaList as $sachMua) {
    $MaSach = $sachMua['MaSach'];
    $SoLuong = $sachMua['SoLuong'];
    $DonGia = $sachMua['DonGiaBan'];
    $sqlmadg = "SELECT taoMaDanhGiamoi() AS ma_danh_gia";
    $resultdg = $conn->query($sqlmadg);

    if ($resultdg && $resultdg->num_rows > 0) {
        // Lấy kết quả
        $row = $resultdg->fetch_assoc();
        $MaDanhGia =$row['ma_danh_gia'];
    } else {
        echo "Không có kết quả hoặc lỗi truy vấn.";
    }
    // Thêm từng sản phẩm vào bảng ChiTietDonHang
    $sqlctdh = "INSERT INTO ChiTietDonHang (MaDonHang, MaSach, SoLuong, DonGia, MaDanhGia) 
    VALUES (?, ?, ?, ?, ?)";
    $stmtCT = $conn->prepare($sqlctdh);
    $stmtCT->bind_param("ssdds", $MaDonHang, $MaSach, $SoLuong, $DonGia, $MaDanhGia);

    $sqlctgh= "DELETE FROM ChiTietGioHang WHERE MaGioHang=? AND MaSach=?";
    $stmtCTGH = $conn->prepare($sqlctgh);
    $stmtCTGH->bind_param("ss", $MaGioHang, $MaSach);
    if (!$stmtCT->execute()) {
        echo json_encode(array("message" => "Thêm sản phẩm vào ChiTietDonHang thất bại.", JSON_UNESCAPED_UNICODE));
        $stmt->close();
        $stmtCT->close();
        $stmtCTGH->close();
        $conn->close();
        exit;
    }
    if (!$stmtCTGH->execute()) {
        echo json_encode(array("message" => "Xóa sản phẩm vào ChiTietGioHang thất bại.", JSON_UNESCAPED_UNICODE));
        $stmt->close();
        $stmtCT->close();
        $stmtCTGH->close();
        $conn->close();
        exit;
    }
}
// Thành công
echo json_encode(array("message" => "Thêm đơn hàng thành công."), JSON_UNESCAPED_UNICODE);
$stmtdiem = $conn->prepare("CALL CongDiem(?, ?)");
$stmtdiem->bind_param("sd", $MaKhachHang, $TongTien);
$stmtdiem->execute();
$stmtdiem->close();
$stmt->close();
$stmtCT->close();
$stmtCTGH->close();
$conn->close();
?>