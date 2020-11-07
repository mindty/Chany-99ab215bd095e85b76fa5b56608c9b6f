<?php

include 'connect.php';

$username  = $_POST['username'];
$login_state   = $_POST['login_state'];

// query SQL untuk update data
$query="UPDATE user WHERE username='$username' SET login_state='$login_state'"

mysqli_query($connect,$usersql);

?>