<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>

	<h1>Login</h1>
	
	<form method="post" action="<%=request.getContextPath() %>/users">
	<input type="hidden" name="a" value="login">
	<label for="email">이메일</label>
	<input name="email" id="email" type="text" /> <br/>
	<label for="password">비밀번호</label>
	<input name="password" id="password" type="password" /> <br/>
	<input type="submit" value="로그인"/>
	</form>
	

</body>
</html>