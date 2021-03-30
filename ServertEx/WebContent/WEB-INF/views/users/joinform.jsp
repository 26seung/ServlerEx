<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
	<h1>회원 가입</h1>
	<!--  /users 서블릿에 POST 방식으로 폼을 전달 -->
	<form name="joinform" method="post" action="<%=request.getContextPath() %>/users">
	<!--  액션 히든 필드 -->
	<input type="hidden" name="a" value="join" />
	<!--  이름 필드 -->
	<label for="name">이름</label>
	<input id="name" name="name" type="text" /><br/>
	<!--  비밀번호 필드 -->
	<label for="passwoed">비밀번호</label>
	<input id="password" name="password" type="password" /><br/>
	<!--  이메일 필드 -->
	<label for="email">이메일</label>
	<input id="email" name="email" type="text" /><br/>
	<!--  성별 필드 : (M or F)-->
	<fieldset>
		<legend>성별</legend>
	<!--  checked 속성 부여 : 미리 체크 -->
		<label>여</label> <input type="radio" name="gender" value="F" checked/>
		<label>남</label> <input type="radio" name="gender" value="M"/>
	</fieldset>
	
	<!-- 전송 버튼 -->
	<input type="submit" value="가입하기" />
	
	
	</form>
	
<%@ include file="/WEB-INF/views/includes/footer.jsp" %>