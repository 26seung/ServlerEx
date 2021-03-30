
<%@page import="com.bit.servlet.dao.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%
	// 사용자 정보 확인
	UserVo authUser = (UserVo)session.getAttribute("authUser");
	%>
	
	
<!--  Navigation -->
	<ul>
		<%
		if(authUser == null){ %>
		<li><a href="<%= request.getContextPath() %>/users?a=loginform">로그인</a></li>
		<li><a href="<%= request.getContextPath() %>/users?a=joinform">회원가입</a></li>
		<% } else { %>
		<li><%=authUser.getName() %> 님 환영합니다..</li>
		<li><a href="<%= request.getContextPath() %>/users?a=logout">로그아웃</a></li>
		<% } %>
	</ul>
	