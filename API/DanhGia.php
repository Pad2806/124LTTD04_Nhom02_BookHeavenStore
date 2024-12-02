<?php
include("connect.php");
// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');

$maDG = $_GET['MaDanhGia'];
$danhGia = $_GET['DanhGia'];
$noiDung = $_GET['NoiDung'];

$sql = "UPDATE ChiTietDonHang
        SET DanhGia=?, NoiDung=?
        WHERE MaDanhGia=?";

$stmt = $conn->prepare($sql);
$stmt->bind_param("dss", $danhGia, $noiDung, $maDG);

// Kiểm tra kết quả của câu lệnh SQL
if ($stmt->execute()) {
    echo json_encode(array("message" => "Thêm đánh giá sản phẩm thành công.", JSON_UNESCAPED_UNICODE));
} else {
    echo json_encode(array("message" => "Thêm đánh giá sản phẩm thất bại.", JSON_UNESCAPED_UNICODE));
}

$stmt->close();
$conn->close();  // Đóng kết nối sau khi xong
?>