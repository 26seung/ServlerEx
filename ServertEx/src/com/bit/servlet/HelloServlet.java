package com.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet 은 HelloServlet 을 상속받아 필요한 메서드를 오버라이드 한다.
// url패턴
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 요청을 처리하는 메서드 오버라이드
		// 클라이언트에서 name 파리머를 전송 받아서 환영 메서드 출력
		// JSP 에서는 사용자 요청을 자동으로 request 라는 이름으로 전달해 주지만
		// 		-> Servlet 에서는 인수로 전달된 req
		String name = req.getParameter("name");
		if (name == null) {
			name = "Anonymous!!";
		}
		// 출력
		// 웅답 객체에서 Writer 을 얻어 온다.
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello,Servlet</h1>");
		out.println("<p>Hello, "+name+"</p>");
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post방식 요청을 처리하기 위한 메서드
		// 폼으로 전송된 파라미터 받아오기
		// 폼의 입력 데이터의 인코딩을 맞춰준다
//		req.setCharacterEncoding("utf-8");			// -> Encoding Filter에서 처리
		// 		폼 내의 input 태그의 name 속성
		String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		
		// 응답 객체 에서 Writer 을 얻어온다
		// 응답 되는 데이터가 text/html임을 브라우저에게 알림
//		resp.setContentType("text/html;charset=utf-8");		-> Encoding Filter에서 처리
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>Form Data</h1>");
		out.println("<p>성: "+lastName+"</p>");
		out.println("<p>이름: "+firstName+"</p>");
		
		
	}

}
