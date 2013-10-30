<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/login">
用户名：<input name="username" type="text" /><br/>
密码：<input name="password" type="password" /><br/>
<input value="submit" type="submit"/>
</form>
</body>
</html>