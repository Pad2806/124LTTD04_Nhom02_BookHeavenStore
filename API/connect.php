<?php
 $host = "localhost";
 $user  = "root";
 $pass = "";
 $database = "heavenbook";
 $conn = mysqli_connect($host, $user, $pass, $database);
 mysqli_set_charset($conn,"utf8");
 if ($conn->connect_error) {
    // Nếu kết nối thất bại, trả về JSON với thông báo lỗi
    header('Content-Type: application/json');
    echo json_encode(array('error' => 'Kết nối thất bại: ' . $conn->connect_error));
    exit(); // Dừng thực thi mã sau khi trả về lỗi
}
?>