<?php

include 'connect.php';

if (isset($_POST['username'])&&isset($_POST['password'])&&isset($_POST['login_time'])&&isset($_POST['login_state'])){
	
   $username = $_POST['username'];
   $password = $_POST['password'];
   $login_time	= $_POST['login_time'];
   $login_state	= $_POST['login_state'];

 /*Cek username pada database sama dengan yang diinputkan atau tidak*/
 $usersql = "SELECT * FROM user WHERE username='$username'";
 $cekuser = mysqli_fetch_array(mysqli_query($connect,$usersql));
 
  /*Cek password pada database sama dengan yang diinputkan atau tidak*/
 $passsql = "SELECT * FROM user WHERE password='$password'";
 $cekpass = mysqli_fetch_array(mysqli_query($connect,$passsql));
 
 
 if (isset ($cekuser)&&isset ($cekpass)){
    /*Jika username dan password sama*/
	$insert = "insert into user (login_time, login_state) values ('$login_time','$login_state')";
	 echo 'Login Success';
	 } else {
		 /*Jika username dan password tidak sama*/
		 echo 'Try Again';
		}
		
} else{
	echo 'Try Again';
	}
?>