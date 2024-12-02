<?php
include("connect.php");
$maKH = $_GET['MaKhachHang'];
// $maKH = isset($_POST['MaKhachHang']) ? $_POST['MaKhachHang'] : 'KH00000001';
$sql = "SELECT ChiTietDonHang.*,TenSach,DonGiaBan,HinhAnh,NgayDatHang,GioDatHang, TrangThai
        FROM ChiTietDonHang,donhang,sach 
        WHERE TrangThai<>N'Đã giao' 
            and MaKhachHang=?
            and DonHang.MaDonHang=ChiTietDonHang.MaDonHang 
            and ChiTietDonHang.MaSach=Sach.MaSach
        ORDER BY NgayDatHang DESC, GioDatHang DESC;";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maKH);
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