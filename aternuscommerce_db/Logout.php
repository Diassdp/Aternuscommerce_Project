<?php
session_start(); 
if (isset($_SESSION['user_id'])) {
    session_destroy();
    $response = array('success' => true, 'message' => 'Logout Successful');
} else {
    $response = array('success' => false, 'message' => 'User not logged in');
}
echo json_encode($response);
?>