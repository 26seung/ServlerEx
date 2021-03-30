package com.bit.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/cookies")
public class CookieTestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 a로 분기
		String action = req.getParameter("a");
		
		if("delete".equals(action)) {
			//	a= delete : 쿠키 삭제 코드
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				for(Cookie cookie:cookies) {
					if(cookie.getName().equals("example")) {
						// 쿠키 삭제 	-> 유효 시간을 0 으로 맞춘다
						cookie.setMaxAge(0);
						resp.addCookie(cookie);
						break;
					}
				}
			}
		} else {
			//			쿠키 읽기: req.getCookies
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {		// 쿠키가 있다
				for(Cookie cookie: cookies) {		// 세팅했던 example cookie 를 찾아봅니다.
					if(cookie.getName().equals("example")) {
						// 쿠키 값 받아오기
						String cookieVal = URLDecoder.decode(cookie.getValue());
						// 속성에 추가
						req.setAttribute("example", cookieVal);
						break;
								
					}
				}
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/cookies/cookie_form.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 쿠키로 설정할 파라미터 읽어오기
		String value = req.getParameter("example");
		value = URLEncoder.encode(value, "UTF-8");
		// Cookie 생성
		Cookie cookie = new Cookie("example", value);		// "example 키에 value 저장
		cookie.setMaxAge(3600);								// 지속 시간 3600초
		// 	사용자 브라우저에 쿠키 저장 위해 응답 헤더에 적재
		resp.addCookie(cookie);
		
		resp.sendRedirect(req.getContextPath() + "/cookies");
	}

	
}
