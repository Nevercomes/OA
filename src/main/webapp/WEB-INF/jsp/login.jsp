<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script src="plugins/jquery/jquery-3.2.1.min.js"></script>
</head>
<body ondragstart="return false" onload="f()">
	<img src="image/bg.jpg" alt="bg" class="bg">
	<div class="board">
		<div class="logo">
			<img src="image/logo.png" alt="logo" style="width: 80%;">
		</div>
		<form action="login/do" id="form" method="post">
			<div class="username">
				<img src="image/person.png" alt="person" class="ico">
				<input type="text" name="userId" placeholder="请输入账号" class="input" id="text-login-id">
			</div>
			<div class="username">
				<img src="image/lock.png" alt="lock" class="ico">
				<input type="password" name="password" placeholder="请输入密码" class="input" id="pwd-login">
			</div>
			<div class="forget"><a style="float: right;"></a></div>
			<input type="submit" value="登录" class="button" >
		</form>
	</div>
</body>
<script src="js/account/login.js"></script>
</html>