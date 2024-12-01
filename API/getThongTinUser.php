<?php
include("connect.php");
$maKH = $_GET['MaKhachHang'];
// $maKH='KH00000001';
$sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ? ";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maKH);
$stmt->execute();
$result = $stmt->get_result();
$data = array(); // Mảng để lưu dữ liệu
if ($result->num_rows > 0) {
    $data = $result->fetch_assoc(); // Lấy một sản phẩm duy nhất
}
// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_UNESCAPED_SLASHES);
$conn->close();  // Đóng kết nối sau khi xong
?>