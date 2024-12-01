<?php
// check_user.php
include("connect.php");
$email = $_GET['Email'];
$sdt = $_GET['SDT'];
$response = "";

// Kiểm tra email
$sqlEmail = "SELECT Email FROM KhachHang WHERE Email = ?";
$stmt = $conn->prepare($sqlEmail);
$stmt->bind_param("s", $email);
$stmt->execute();
$resultEmail = $stmt->get_result();

if ($resultEmail->num_rows > 0) {
    $response.="Email";
}

// Kiểm tra số điện thoại
$sqlPhone = "SELECT SDT FROM KhachHang WHERE SDT = ?";
$stmt = $conn->prepare($sqlPhone);
$stmt->bind_param("s", $sdt);
$stmt->execute();
$resultPhone = $stmt->get_result();

if ($resultPhone->num_rows > 0) {
    $response.="SDT";
}

echo json_encode(array("message" => $response,JSON_UNESCAPED_UNICODE));
?>