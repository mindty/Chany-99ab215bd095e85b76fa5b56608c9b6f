<?php

include 'connect.php';

if (isset($_POST['login_state'])){
	
   $login_state = $_POST['login_state'];

 /*Cek login state*/
 $usersql = "SELECT * FROM user WHERE login_state='$login_state'";
 $cekuser = mysqli_fetch_array(mysqli_query($connect,$usersql));
 
 if (isset ($login_state)){
    /*Jika login state sama*/
	 echo 'Still Login';
	 } else {
		 /*Jika login state tidak sama*/
		 echo 'Login Again';
		}
		
} else{
	echo 'Try Again';
	}
?>