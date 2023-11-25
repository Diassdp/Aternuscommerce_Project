<?php
if (!empty($_POST['user_id'])) {
    $user_id = $_POST['user_id'];

    $con = mysqli_connect('localhost', 'root', '', 'aternuscommerce_db');

    if ($con) {
        $sql = "SELECT fullname, email FROM users WHERE user_id = ?";
        $stmt = mysqli_prepare($con, $sql);

        if ($stmt) {
            mysqli_stmt_bind_param($stmt, 'i', $user_id);
            mysqli_stmt_execute($stmt);

            $result = mysqli_stmt_get_result($stmt);

            if ($result) {
                if (mysqli_num_rows($result) > 0) {
                    // Data retrieval successful
                    $row = mysqli_fetch_assoc($result);
                    $full_name = $row['fullname'];
                    $email = $row['email'];
                    $response = array('success' => true, 'user_id' => $user_id, 'fullname' => $full_name, 'email' => $email, 'message' => 'Obtain Data');
                    echo json_encode($response);
                } else {
                    // No data found
                    $response = array('success' => false, 'message' => 'No data found for the user');
                    echo json_encode($response);
                }
            } else {
                // Error in SQL query
                $response = array('success' => false, 'message' => 'Error in query execution');
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
    // Invalid or missing user_id parameter
    $response = array('success' => false, 'message' => 'Invalid or missing user_id parameter');
    echo json_encode($response);
}
?>
