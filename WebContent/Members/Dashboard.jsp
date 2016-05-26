<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserProfile" %>
<%
	String htm;
	HttpSession ses = request.getSession();
	UserProfile user = (UserProfile) ses.getAttribute("UserProfile");
	if(user == null){
		htm = "ログアウトされました。再度<a href=\"login\">ログイン</a>してください。";
	}else{
		htm = "ようこそ！<form action = \"MainMenu\" method = \"POST\">";
		htm += "<input type=\"hidden\" name=\"action\" value=\"logout\"><input type=\"submit\" value=\"logout\"></form>";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
ようこそ！
<form action = "MainMenu" method = "POST">
<input type="hidden" name="action" value="logout"><input type="submit" value="logout">
</form>
</body>
</html>