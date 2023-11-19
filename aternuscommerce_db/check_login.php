<?php
session_start();
if (isset($_SESSION['user_id'])) {
    // User is logged in
    $response = array('loggedIn' => true, 'user_id' => $_SESSION['user_id']);
    echo json_encode($response);
} else {
    // User is not logged in
    $response = array('loggedIn' => false);
    echo json_encode($response);
}
?>
