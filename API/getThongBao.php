<?php
include("connect.php");
$makh = $_GET['MaKhachHang'];

// SQL query để lấy các thông báo cho khách hàng
$sql = "SELECT NoiDungTB, NgayTB, GioTB
        FROM ThongBao, KhachHang
        WHERE ThongBao.MaKhachHang = KhachHang.MaKhachHang
        AND KhachHang.MaKhachHang = ?
        ORDER BY NgayTB DESC, GioTB DESC";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $makh);
$stmt->execute();
$result = $stmt->get_result();

// Mảng để lưu các thông báo
$data = array();

if ($result->num_rows > 0) {
    // Lấy tất cả thông báo và lưu vào mảng
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);

// Đóng kết nối sau khi xong
$conn->close();
?>