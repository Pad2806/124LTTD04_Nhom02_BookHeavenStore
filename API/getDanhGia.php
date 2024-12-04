<?php
include("connect.php");
$maSach = $_GET['MaSach'];
$sql = "SELECT TenKhachHang,DanhGia,NoiDung 
        FROM KhachHang,DonHang,ChiTietDonHang
        WHERE KhachHang.MaKhachHang=DonHang.MaKhachHang
        AND DonHang.MaDonHang=ChiTietDonHang.MaDonHang
        AND ChiTietDonHang.MaSach=?
        AND DanhGia IS NOT NULL
        AND NoiDung IS NOT NULL";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maSach);
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
echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_UNESCAPED_SLASHES);
$conn->close();  // Đóng kết nối sau khi xong
?>