<?php
include("connect.php");
$maDH = $_POST['MaDonHang'];
// $maDH = isset($_POST['MaDonHang']) ? $_POST['MaDonHang'] : 'DH00000001';
$sql = "SELECT * FROM ChiTietDonHang 
        WHERE MaDonHang = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maDH);
$stmt->execute();
$result = $stmt->get_result();


$data = array(); // Mảng để lưu dữ liệu

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $data[] = $row; // Thêm từng hàng vào mảng
    }
}

// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data,JSON_UNESCAPED_UNICODE);
$conn->close();  // Đóng kết nối sau khi xong
?>