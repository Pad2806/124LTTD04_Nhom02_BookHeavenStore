<?php
include("connect.php");
$maDG = $_GET['MaDanhGia'];
$sql = "SELECT MaDanhGia,TenSach,TenTacGia,Sach.HinhAnh,DanhGia,NoiDung
        FROM ChiTietDonHang,sach,tacgia
        WHERE MaDanhGia=?
            and ChiTietDonHang.MaSach=Sach.MaSach
            and Sach.MaTacGia=tacgia.MaTacGia";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $maDG);
$stmt->execute();
$result = $stmt->get_result();


$data = array(); // Mảng để lưu dữ liệu

if ($result->num_rows > 0) {
    $data = $result->fetch_assoc(); // Lấy 1 đối tượng duy nhất
    if (is_null($data['NoiDung'])) {
        $data['NoiDung'] = "NULL";
    }
    if (is_null($data['DanhGia'])) {
        $data['DanhGia'] = "0";
    }
}

// Trả về dữ liệu dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_UNESCAPED_SLASHES);
$conn->close();  // Đóng kết nối sau khi xong
?>