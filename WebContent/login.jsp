<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.LoginInput" %>
<% LoginInput input = (LoginInput) request.getAttribute("Input");
	String errorMsg;
	if(input==null){
		errorMsg = "";
	}else{
		errorMsg = input.geterrorMsg();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	html,body{
		width:100%;
		height:100%;
		margin:0;
		padding:0;
	}
	html{
		display: table;
	}
	body{
		background-image: url('images/urawa.jpg');
		background-size: 100% 100%;
		display: table-cell;
		text-align: center;
		vertical-align: middle;
	}
	div{
		background: #FFFFFF;
		width: 300px;
		height: 150px;
		margin:0 auto 0 auto;
		padding: 10px;
	}
</style>
<title>login</title>
</head>
<body>
<div>
<%= errorMsg %>
<form action="login" method="POST">
	ユーザーID<br />
	<input type="text" name="userId"><br />
	パスワード<br />
	<input type="password" name="userPass"><br /><br />
	<input type="submit" value="ログイン">
</form>
<a href="entry">ユーザー登録はこちら</a>
</div>
</body>
</html>