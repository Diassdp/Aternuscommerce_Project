<?php
if (!empty($_POST['user_id']) && !empty($_POST['fullname']) && !empty($_POST['email']) && !empty($_POST['password'])) {
    $user_id = $_POST['user_id'];
    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $con = mysqli_connect('localhost', 'root', '', 'aternuscommerce_db');

    if ($con) {
        $sql = "UPDATE users SET fullname = ?, email = ?, password = ? WHERE user_id = ?";
        $stmt = mysqli_prepare($con, $sql);

        if ($stmt) {
            mysqli_stmt_bind_param($stmt, 'sssi', $fullname, $email, $password, $user_id);
            mysqli_stmt_execute($stmt);

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
        } else {
            // Error in prepared statement
            $response = array('success' => false, 'message' => 'Error in prepared statement');
            echo json_encode($response);
        }

        mysqli_close($con);
    } else {
        // Failed to connect to the database
        $response = array('success' => false, 'message' => 'Failed To Connect');
        echo json_encode($response);
    }
} else {
    // Invalid request, user_id, fullname, email, or password not provided
    $response = array('success' => false, 'message' => 'Invalid request');
    echo json_encode($response);
}
?>
