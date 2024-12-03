<?php
include("connect.php");

// Lấy danh sách mã sách dưới dạng chuỗi phân cách dấu phẩy
$maKhachHang = $_GET['MaKhachHang']; 
$maSachString = $_GET['DSSachMua']; 
// $maSachString ='BOOK000023';
$maSachArray = explode(',', $maSachString);

// Kiểm tra nếu mã sách hợp lệ
if (count($maSachArray) > 0) {
    // Tạo câu truy vấn SQL với các mã sách
    $placeholders = implode(',', array_fill(0, count($maSachArray), '?'));
    $sql = "SELECT ChiTietGioHang.MaSach, TenSach, SoLuong, DonGiaBan, ThanhTien, Sach.HinhAnh 
            FROM ChiTietGioHang, KhachHang, Sach,GioHang
            WHERE KhachHang.MaGioHang=GioHang.MaGioHang
                AND GioHang.MaGioHang=ChiTietGioHang.MaGioHang
                AND ChiTietGioHang.MaSach = Sach.MaSach
                AND Sach.MaSach IN ($placeholders)
                AND KhachHang.MaKhachHang=?";

    // Chuẩn bị truy vấn
    $stmt = $conn->prepare($sql);
    // Kết hợp các tham số cho câu truy vấn
    $types = str_repeat('s', count($maSachArray)) . 's'; // Các tham số: mã sách là 's' và mã khách hàng là 's'
    $params = array_merge($maSachArray, [$maKhachHang]);

    // Ràng buộc tham số vào truy vấn
    $stmt->bind_param($types, ...$params);
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