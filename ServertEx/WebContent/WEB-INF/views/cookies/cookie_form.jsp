<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 서블릿에서 넘어온 example 객체 확인
String cookieValue = (String)request.getAttribute("example");
System.out.println("COOKIE VALUE:" + cookieValue);
if (cookieValue == null) {
	cookieValue = "";
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Test</title>
</head>
<body>

	<h1>Cookie Test Form</h1>
	
	<h3>Add / Edit  Cookie</h3>
	<form method="post" action="<%= request.getContextPath() %>/cookies">
	<label for="example">Cookie Value</label>
	<input type="text" name="example" id="example" value="<%=cookieValue%>">
	<input type="submit" value="쿠키설정">
	</form>

	<h4>Delete Cookie</h4>
	<p>
		<a href="<%= request.getContextPath() %>/cookies?a=delete">쿠키 삭제</a>
	</p>
	
</body>
</html>