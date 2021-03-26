<%@page import="com.bit.servlet.dao.EmailDaoOrclImpl"%>
<%@page import="com.bit.servlet.dao.EmailDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	// 파라미터 받아오기, Parameter 의 값은 항상 String
Long no = Long.valueOf(request.getParameter("no"));
EmailDao dao = new EmailDaoOrclImpl();

dao.delete(no);

// 리스트 페이지로 리다이렉트
response.sendRedirect(request.getContextPath() + "/emaillist/");

%>