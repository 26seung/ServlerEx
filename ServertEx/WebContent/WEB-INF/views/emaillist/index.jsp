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
	<h1>메일링 리스트(Model 2)</h1>
	
	<%	// 컨투롤러에서 속성으로 추가해준 리스트 객체를 꺼내옵니다.
	// 다운 캐스팅 필요
	List<EmailVo> list = (List<EmailVo>)request.getAttribute("list");
	
	
	// 루프
	for (EmailVo vo : list) {
	%>
	
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
		<!-- 삭제 버튼 -->
		<tr>
			<td colspan="2">
				<form action="<%= request.getContextPath() %>/el">
					<input type="hidden" name="a" value="delete" />
					<!-- 게시물의 no(PK) -->
					<input type="hidden" name="no" value="<%= vo.getNo() %>"/>
					<!-- 전송 버튼 -->
					<input type="submit" value="삭제" />
				</form>
				<!-- TODO: 수정 기능을 구현해보기 -->
			</td>
		</tr>
	</table>		
		
		<%
		}
		%>
	
	<p>
		<a href="<%= request.getContextPath() %>/el?a=form">메일링 리스트 가입(MVC)</a>
	</p>
	
</body>
</html>