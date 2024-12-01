<?php
include("connect.php");
header('Content-Type: application/json; charset=UTF-8');
// Gán dữ liệu từ request
$email = $_GET['Email'];
$sdt = $_GET['SDT'];
$matkhau = $_GET['MatKhau'];
// Kiểm tra dữ liệu đầu vào
if (empty($email) || empty($sdt) || empty($matkhau)) {
    echo json_encode(array("message" => "Vui lòng cung cấp đầy đủ Email, SĐT và Mật khẩu.", JSON_UNESCAPED_UNICODE));
    exit;
}

// Tạo mã khách hàng mới
$sqlMaKhachHang = "SELECT taoMaKhachHangmoi() AS ma_khach_hang";
$resultKhachHang = $conn->query($sqlMaKhachHang);
if ($resultKhachHang && $resultKhachHang->num_rows > 0) {
    $rowKhachHang = $resultKhachHang->fetch_assoc();
    $MaKhachHang = $rowKhachHang['ma_khach_hang'];
} else {
    echo json_encode(array("message" => "Không thể tạo mã khách hàng.", JSON_UNESCAPED_UNICODE));
    exit;
}

// Tạo mã giỏ hàng mới
$sqlMaGioHang = "SELECT taoMaGioHangmoi() AS ma_gio_hang";
$resultGioHang = $conn->query($sqlMaGioHang);
if ($resultGioHang && $resultGioHang->num_rows > 0) {
    $rowGioHang = $resultGioHang->fetch_assoc();
    $MaGioHang = $rowGioHang['ma_gio_hang'];
} else {
    echo json_encode(array("message" => "Không thể tạo mã giỏ hàng.", JSON_UNESCAPED_UNICODE));
    exit;
}
// Thêm giỏ hàng vào bảng GioHang
$sqlInsertGH = "INSERT INTO GioHang (MaGioHang) 
              VALUES (?)";
$stmt = $conn->prepare($sqlInsertGH);
$stmt->bind_param("s",  $MaGioHang);

if ($stmt->execute()) {
    echo json_encode(array("message" => "Thêm giỏ hàng thành công.", JSON_UNESCAPED_UNICODE));
} else {
    echo json_encode(array("message" => "Không thể thêm giỏ hàng.", JSON_UNESCAPED_UNICODE));
}
// Thêm khách hàng vào bảng KhachHang
$sqlInsert = "INSERT INTO KhachHang (MaKhachHang, Email, SDT, MatKhau, MaGioHang, HangThanhVien, NgayDangKy) 
              VALUES (?, ?, ?, ?, ?,'Đồng',CURRENT_DATE)";
$stmt = $conn->prepare($sqlInsert);
$stmt->bind_param("sssss", $MaKhachHang, $email, $sdt, $matkhau, $MaGioHang);

if ($stmt->execute()) {
    echo json_encode(array("message" => "Thêm khách hàng thành công.", JSON_UNESCAPED_UNICODE));
} else {
    echo json_encode(array("message" => "Không thể thêm khách hàng.", JSON_UNESCAPED_UNICODE));
}

// Đóng kết nối
$stmt->close();
$conn->close();
?>