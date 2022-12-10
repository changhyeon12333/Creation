<?php 
    $con = mysqli_connect("localhost", "자신의 아이디", "자신의 비밀번호", "자신의 아이디");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userName = $_POST["userName"];
    $userCarnum = $_POST["userCarnum"];
    

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssiis", $userID, $userPassword, $userCarnum);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>