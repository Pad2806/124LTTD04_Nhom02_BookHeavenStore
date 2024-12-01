<?php
include("connect.php");

// Lấy danh sách mã sách dưới dạng chuỗi phân cách dấu phẩy
$maSachString = $_GET['DSSachMua']; 
// $maSachString ='BOOK000023';
$maSachArray = explode(',', $maSachString);

// Kiểm tra nếu mã sách hợp lệ
if (count($maSachArray) > 0) {
    // Tạo câu truy vấn SQL với các mã sách
    $placeholders = implode(',', array_fill(0, count($maSachArray), '?'));
    $sql = "SELECT ChiTietGioHang.MaSach, TenSach, SoLuong, DonGiaBan, ThanhTien, HinhAnh 
            FROM ChiTietGioHang
            JOIN Sach ON ChiTietGioHang.MaSach = Sach.MaSach
            WHERE Sach.MaSach IN ($placeholders)";

    // Chuẩn bị truy vấn
    $stmt = $conn->prepare($sql);
    $stmt->bind_param(str_repeat('s', count($maSachArray)), ...$maSachArray);
    $stmt->execute();
    $result = $stmt->get_result();

    $data = array();
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }

    // Trả về dữ liệu dưới dạng JSON
    header('Content-Type: application/json; charset=UTF-8');
    echo json_encode($data, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
} else {
    echo json_encode(array("error" => "Danh sách mã sách không hợp lệ."));
}

$conn->close();
?>