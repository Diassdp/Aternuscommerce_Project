<?php
session_start();

if (!empty($_POST['username']) && !empty($_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $con = mysqli_connect('localhost', 'root', '', 'aternuscommerce_db');

    if ($con) {
        // Use prepared statement to prevent SQL injection
        $sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
        $stmt = mysqli_prepare($con, $sql);

        if ($stmt) {
            mysqli_stmt_bind_param($stmt, 'ss', $username, $password);
            mysqli_stmt_execute($stmt);

            $result = mysqli_stmt_get_result($stmt);

            if ($result) {
                if (mysqli_num_rows($result) > 0) {
                    // Login successful
                    $row = mysqli_fetch_assoc($result);
                    $user_id = $row['user_id'];

                    // Set user_id in the session
                    $_SESSION['user_id'] = $user_id;

                    $response = array('success' => true, 'user_id' => $user_id, 'message' => 'Login Successful');
                    echo json_encode($response);
                } else {
                    // Invalid Login
                    $response = array('success' => false, 'message' => 'Invalid Login');
                    echo json_encode($response);
                }
            } else {
                // Error in SQL query
                $response = array('success' => false, 'message' => 'Error in query execution: ' . mysqli_error($con));
                echo json_encode($response);
            }

            mysqli_stmt_close($stmt);
        } else {
            // Error in prepared statement
            $response = array('success' => false, 'message' => 'Error in prepared statement: ' . mysqli_error($con));
            echo json_encode($response);
        }

        mysqli_close($con);
    } else {
        // Failed to connect to the database
        $response = array('success' => false, 'message' => 'Failed To Connect: ' . mysqli_connect_error());
        echo json_encode($response);
    }
}
?>
