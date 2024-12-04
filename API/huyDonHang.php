<?php
include("connect.php");
// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
$MaKhachHang = $_GET['MaKhachHang'];
$madh = $_GET['MaDonHang'];
// $madh='DH00000031';

$sql = "UPDATE DonHang
        SET TrangThai='Đã hủy'
        WHERE MaDonHang=?";

$stmt = $conn->prepare($sql);
$stmt->bind_param("s",  $madh);

// Kiểm tra kết quả của câu lệnh SQL
if ($stmt->execute()) {
    echo json_encode(array("message" => "Hủy đơn hàng thành công.", JSON_UNESCAPED_UNICODE));
} else {
    echo json_encode(array("message" => "Hủy đơn hàng thất bại.", JSON_UNESCAPED_UNICODE));
}
$stmtdiem = $conn->prepare("CALL TruDiem(?, ?)");
$stmtdiem->bind_param("ss", $MaKhachHang, $madh);
$stmtdiem->execute();
$stmtdiem->close();
$stmt->close();
$conn->close();  // Đóng kết nối sau khi xong
?>