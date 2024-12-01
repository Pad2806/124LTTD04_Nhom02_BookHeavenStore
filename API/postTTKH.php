<?php
include("connect.php");
$maKH = $_POST['MaKhachHang'];
$tenKH = $_POST['TenKhachHang'];
$diachi = $_POST['DiaChi'];
$email = $_POST['Email'];
$sdt = $_POST['SDT'];
$matkhau = $_POST['MatKhau'];
$ngaysinh = $_POST['NgaySinh'];
$sql = "UPDATE KhachHang 
        SET TenKhachHang = ?,
            DiaChi = ?,
            SDT = ?, 
            Email = ?, 
            NgaySinh = ?, 
            MatKhau = ?
        WHERE MaKhachHang = ?";
if(isset($_POST['MaKhachHang']) &&isset($_POST['TenKhachHang']) && isset($_POST['DiaChi']) && isset($_POST['Email']) && 
    isset($_POST['SDT']) && isset($_POST['MatKhau']) && isset($_POST['NgaySinh'])){
    // Chuẩn bị câu truy vấn
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("sssssss", $tenKH, $diachi, $sdt, $email, $ngaysinh, $matkhau, $maKH);
    // Trả về kết quả dưới dạng JSON
    header('Content-Type: application/json; charset=UTF-8');
    // Thực thi câu truy vấn
    if ($stmt->execute()) {
        echo json_encode(array("message" => "Cập nhật thông tin khách hàng thành công.",JSON_UNESCAPED_UNICODE));
    } else {
        echo json_encode(array("message" => "Không thể cập nhật thông tin khách hàng."));
    }
}
else{
    echo json_encode(array("message" => "Không đủ thông tin khách hàng."));
}
$conn->close();  // Đóng kết nối sau khi xong
?>