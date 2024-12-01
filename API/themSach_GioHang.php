<?php
include("connect.php");

// Lấy dữ liệu từ GET request
$maGioHang = $_GET['MaGioHang'];
$maSach = $_GET['MaSach'];
$soLuong = $_GET['SoLuong'];
$donGia = $_GET['DonGia'];


// Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
$sql_check = "SELECT * FROM ChiTietGioHang WHERE MaGioHang = ? AND MaSach = ?";
$stmt_check = $conn->prepare($sql_check);
$stmt_check->bind_param("ss", $maGioHang, $maSach);
$stmt_check->execute();
$result_check = $stmt_check->get_result();

if ($result_check->num_rows > 0) {
    // Sản phẩm đã có trong giỏ hàng, cập nhật số lượng
    $row = $result_check->fetch_assoc();
    $newSoLuong = $row['SoLuong'] + $soLuong;  // Cộng thêm số lượng

    $sql_update = "UPDATE ChiTietGioHang SET SoLuong = ? WHERE MaGioHang = ? AND MaSach = ?";
    $stmt_update = $conn->prepare($sql_update);
    $stmt_update->bind_param("iss", $newSoLuong, $maGioHang, $maSach);
    $stmt_update->execute();

    echo json_encode(array("message" => "Cập nhật số lượng sản phẩm thành công."), JSON_UNESCAPED_UNICODE);
} else {
    // Sản phẩm chưa có trong giỏ hàng, thêm mới
    $sql_insert = "INSERT INTO ChiTietGioHang(MaGioHang, MaSach, SoLuong, ThanhTien) VALUES (?, ?, ?, ?)";
    $stmt_insert = $conn->prepare($sql_insert);
    $stmt_insert->bind_param("ssii", $maGioHang, $maSach, $soLuong, $donGia);
    $stmt_insert->execute();

    echo json_encode(array("message" => "Thêm sách vào giỏ hàng thành công."), JSON_UNESCAPED_UNICODE);
}

// Đóng kết nối
$stmt_check->close();
$stmt_insert->close();
$stmt_update->close();
$conn->close();
?>
