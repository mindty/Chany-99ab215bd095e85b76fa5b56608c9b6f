<?php 
$host ="localhost"; //atau bisa di isi dengan ip localhost 127.0.0.1
$user ="root"; 		//id user, karena kita menggunakan localhost, nama usernya di isi root
$pass =""; 			//pasword kita kosongi
$database ="tes";

$connect=mysqli_connect($host,$user,$pass,$database) or die ("gagal"); 
/*untuk mengkoneksikan dengan database yang telah kita buat tadi,dengan menginputkan host, user, pass, database dengan perintah mysqli_connect,
 jika gagal maka muncul pemberitahuan "koneksi gagal"*/
?>