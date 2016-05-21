<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg;
	if(request.getAttribute("errorMsg") == null){
		errorMsg = "";
	}
	else{
		errorMsg = (String)request.getAttribute("errorMsg");
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
		height: 200px;
		margin:0 auto 0 auto;
		padding 10px auto 10px auto;
	}
</style>
<title>ユーザー登録</title>
</head>
<body>
<div>
<%= errorMsg %><br />
<form action="entry" method="POST">
	メールアドレスを入力してください。<br />
	<input type="text" name="mailAddr"><br />
	ご希望のユーザーIDを入力してください。<br />
	<input type="text" name="userId"><br />
	ご希望のパスワードを入力してください。<br />
	<input type="password" name="userPass"><br /><br />
	<input type="submit" value="ユーザー登録">
</form>
<a href="login.jsp">ログイン画面に戻る</a>
</div>
</body>
</html>