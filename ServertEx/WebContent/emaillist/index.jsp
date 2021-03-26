<%@page import="com.bit.servlet.dao.EmailVo"%>
<%@page import="java.util.List"%>
<%@page import="com.bit.servlet.dao.EmailDao"%>
<%@page import="com.bit.servlet.dao.EmailDaoOrclImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일링 리스트 확인</title>
</head>
<body>

	<h1>메일링 리스트</h1>
	
	<%		// DAO 불러오기
	EmailDao dao = new EmailDaoOrclImpl();
			// 목록 불러오기
	List<EmailVo>list = dao.getList();
	
			
	for(EmailVo vo : list) {
		// 출력 %>
		<!-- 정보 테이블 -->
		<table border=1>
			<tr>
				<th>성</th>
				<td><%= vo.getLastName() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%= vo.getFirstName() %></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%= vo.getEmail() %></td>
			</tr>
		</table>	
		
		<% 
	}
		%>
	
	<p>
		<a href="form.jsp">메일링 리스트 가입</a>
	</p>
	

	
</body>
</html>