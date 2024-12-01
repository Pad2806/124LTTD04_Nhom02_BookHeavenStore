<?php
include("connect.php");
// Lấy dữ liệu từ GET request
$maGioHang = $_GET['MaGioHang'];
$maSach = $_GET['MaSach'];
$soLuong = $_GET['SoLuong'];
// Cập nhật số lượng trong bảng ChiTietGioHang
$sql = "UPDATE ChiTietGioHang SET SoLuong = ? WHERE MaGioHang = ? AND MaSach = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("iis", $soLuong, $maGioHang, $maSach);
$executeResult = $stmt->execute();
$data = array();  // Mảng để lưu dữ liệu
if ($executeResult) {
    $data['message'] = 'Cập nhật thành công';
} else {
    $data['message'] = 'Cập nhật thất bại: ' . $stmt->error;
}
// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
// Đóng kết nối
$stmt->close();
$conn->close();
?>
