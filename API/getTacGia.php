<?php
include("connect.php");
$sql = "SELECT * FROM TacGia";
$result = $conn->query($sql);

$data = array(); // Mảng để lưu dữ liệu

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $data[] = $row; // Thêm từng hàng vào mảng
    }
}
// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_UNESCAPED_SLASHES);
$conn->close();  // Đóng kết nối sau khi xong
?>