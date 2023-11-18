<?php
if (!empty($_POST['username'])) {
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $con = mysqli_connect('localhost', 'root', '', 'aternuscommerce_db');

    if ($con) {
        $sql = "INSERT INTO users (fullname, username, email, password) VALUES ('" . $fullname . "', '" . $username . "', '" . $email . "', '" . $password . "')";

        if (mysqli_query($con, $sql)) {
            $response = array('success' => true, 'message' => 'Registration Success');
            echo json_encode($response);
        } else {
            $response = array('success' => false, 'message' => 'Failed Registration');
            echo json_encode($response);
        }
    } else {    
        $response = array('success' => false, 'message' => 'Failed To Connect');
        echo json_encode($response);
    }
}
?>
    