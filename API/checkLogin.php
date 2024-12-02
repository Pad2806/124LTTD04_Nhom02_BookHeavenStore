<?php
include("connect.php");
$username = $_POST['Email'];
$password = $_POST['MatKhau'];
$sql = "SELECT * FROM KhachHang WHERE Email = ? AND MatKhau = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $username, $password);
$stmt->execute();
$result = $stmt->get_result();

$data = array();
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if($row['TrangThai']!=0){
        $data['status'] = 'failure';
        $data['message'] = 'Tài khoản đã bị khóa';
    }else{
        $data['status'] = 'success';
        $data['message'] = 'Đăng nhập thành công';
        $data['MaKhachHang'] = $row['MaKhachHang'];
        $data['TenKhachHang'] = $row['TenKhachHang'];
        $data['MaGioHang'] = $row['MaGioHang'];
    }
} else {
    $data['status'] = 'failure';
    $data['message'] = 'Vui lòng nhập lại thông tin';
}

// Trả về kết quả dưới dạng JSON
header('Content-Type: application/json; charset=UTF-8');
echo json_encode($data,JSON_UNESCAPED_UNICODE|JSON_UNESCAPED_SLASHES);
$stmt->close();
$conn->close();  // Đóng kết nối sau khi xong
?>