<?php
// check_user.php
include("connect.php");
$email = $_GET['Email'];
$sdt = $_GET['SDT'];
$matkhau = $_GET['MatKhau'];
// Kiểm tra email
$sql = "UPDATE KhachHang
        SET MatKhau = ?
        WHERE Email = ? AND  SDT = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sss", $matkhau,$email, $sdt);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    echo json_encode(array("message" => "TRUE",JSON_UNESCAPED_UNICODE));
}else{
    echo json_encode(array("message" => "FALSE",JSON_UNESCAPED_UNICODE));
}

?>