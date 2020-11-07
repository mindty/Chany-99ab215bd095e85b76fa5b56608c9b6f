<?php

include 'connect.php';

if (isset($_POST['username'])&&isset($_POST['password'])){
	
   $username = $_POST['username'];
   $password = $_POST['password'];

 /*untuk mengecek apakah username tersebut sudah ada sebelumnya atau belum*/
 $usersql = "SELECT * FROM user WHERE username='$username'";
 $cekuser = mysqli_fetch_array(mysqli_query($connect,$usersql));
 
 if (isset ($cekuser)){
	  /*Jika username sudah ada*/
	 echo 'Already Register';
 } else {
	 /*Jika username belum ada*/
	 $insert = "insert into user (username, password) values ('$username','$password')";
     
	 if(mysqli_query($connect, $insert)){
		echo 'Done';
		} else{
			echo 'Try Again';
		}
	}
 } else{
	echo 'Try Again';
	}
?>