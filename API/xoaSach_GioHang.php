<?php
include("connect.php");
// Lấy danh sách mã sách từ tham số GET
$maSachString = $_GET['DSSachMua']; 
if (!$maSachString) {
    echo json_encode(array("message" => "Danh sách mã sách không hợp lệ."), JSON_UNESCAPED_UNICODE);
    exit;
}
$maSachArray = explode(',', $maSachString);
// Kiểm tra nếu danh sách mã sách hợp lệ
if (count($maSachArray) > 0) {
    // Tạo câu truy vấn SQL với các mã sách
    $placeholders = implode(',', array_fill(0, count($maSachArray), '?'));
    $sql = "DELETE FROM ChiTietGioHang WHERE MaSach IN ($placeholders)";
    // Chuẩn bị truy vấn
    $stmt = $conn->prepare($sql);
    if ($stmt) {
        // Gắn tham số vào câu truy vấn
        $stmt->bind_param(str_repeat('s', count($maSachArray)), ...$maSachArray);
        // Thực thi truy vấn
        if ($stmt->execute()) {
            echo json_encode(array("message" => "Xóa sách trong giỏ hàng thành công."), JSON_UNESCAPED_UNICODE);
        } else {
            echo json_encode(array("message" => "Lỗi khi xóa sách: " . $stmt->error), JSON_UNESCAPED_UNICODE);
        }
    } else {
        echo json_encode(array("message" => "Lỗi khi chuẩn bị truy vấn."), JSON_UNESCAPED_UNICODE);
    }
} else {
    echo json_encode(array("message" => "Danh sách mã sách không hợp lệ."), JSON_UNESCAPED_UNICODE);
}
$conn->close();
?>
