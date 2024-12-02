<?php
// check_user.php
include("connect.php");
$email = $_GET['Email'];
$sdt = $_GET['SDT'];
// $email = 'QThao@gmail.com';
// $sdt = '0986574823';
// Kiểm tra email
if (empty($email) || empty($sdt) ) {
    echo json_encode(array("message" => "Dữ liệu không đầy đủ.", JSON_UNESCAPED_UNICODE));
    exit;
}
$sql = "SELECT * FROM KhachHang WHERE Email = ? AND  SDT = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $email, $sdt);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    if($row['TrangThai']!=0){
        echo json_encode(array("message" => "LOCK"));
    }else{
        echo json_encode(array("message" => "TRUE"));
    }
}else{
    echo json_encode(array("message" => "FALSE"));
}

?>