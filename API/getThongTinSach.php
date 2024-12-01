<?php
include("connect.php");
$maSach = $_GET['MaSach'];
// $maSach = 'BOOK000047';
$sql = "SELECT Sach.*, TacGia.TenTacGia 
        FROM Sach, TacGia 
        WHERE Sach.MaTacGia = TacGia.MaTacGia AND MaSach = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maSach);
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