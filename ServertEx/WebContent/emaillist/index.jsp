<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
	
	<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String dburl = "jdbc:oracle:thin:@220.85.164.169:1521:xe";
		conn = DriverManager.getConnection(dburl, "SEUNG", "1234");
		stmt = conn.createStatement();
		String sql = "SELECT no, last_name, first_name, email FROM emaillist ORDER BY create_at DESC";
		rs = stmt.executeQuery(sql);
		
		// 루프
		while(rs.next()) {
			long no = rs.getLong(1);
			String last_name = rs.getString(2);
			String first_name = rs.getString(3);
			String email = rs.getString(4);
			//	출력 %>
		<!-- 정보 테이블 -->
	<table border=1>
		<tr>
			<th>성</th>
			<td><%= last_name %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%= first_name %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%= email %></td>
		</tr>
	</table>	
			<%
		}
	} catch (ClassNotFoundException e) {
		
	} catch (SQLException e) {
		%>
		<%= e %>
		<%
	} finally {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			
		}
	}
	%>
	
</body>
</html>