<?php
if (isset($_POST['user_id'], $_POST['fullname'], $_POST['email'])) {
    $user_id = $_POST['user_id'];
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];

    $con = mysqli_connect('localhost', 'root', '', 'aternuscommerce_db');

    if ($con) {
        if (isset($_POST['password']) && !empty($_POST['password'])) {
            // If password not empty
            $password = $_POST['password'];

            $sql = "UPDATE users SET fullname = ?, email = ?, password = ? WHERE user_id = ?";
            $stmt = mysqli_prepare($con, $sql);

            if ($stmt) {
                mysqli_stmt_bind_param($stmt, 'sssi', $fullname, $email, $password, $user_id);
                mysqli_stmt_execute($stmt);
                checkAndUpdateResult($stmt);
            } else {
                // Error in prepared statement
                $response = array('success' => false, 'message' => 'Error in prepared statement');
                echo json_encode($response);
            }
        } else {
            // If password empty
            $sql = "UPDATE users SET fullname = ?, email = ? WHERE user_id = ?";
            $stmt = mysqli_prepare($con, $sql);

            if ($stmt) {
                mysqli_stmt_bind_param($stmt, 'ssi', $fullname, $email, $user_id);
                mysqli_stmt_execute($stmt);
                checkAndUpdateResult($stmt);
            } else {
                // Error in prepared statement
                $response = array('success' => false, 'message' => 'Error in prepared statement');
                echo json_encode($response);
            }
        }

        mysqli_close($con);
    } else {
        // Failed to connect to the database
        $response = array('success' => false, 'message' => 'Failed To Connect');
        echo json_encode($response);
    }
} else {
    // Invalid request, user_id, fullname, email not set
    $response = array('success' => false, 'message' => 'Invalid request');
    echo json_encode($response);
}
function checkAndUpdateResult($stmt) {
    if (mysqli_stmt_affected_rows($stmt) > 0) {
        // Update successful
        $response = array('success' => true, 'message' => 'Update Successful');
        echo json_encode($response);
    } else {
        // No rows affected, likely no changes made
        $response = array('success' => false, 'message' => 'No changes made');
        echo json_encode($response);
    }
    mysqli_stmt_close($stmt);
}
?>
