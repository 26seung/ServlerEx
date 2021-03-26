package com.bit.servlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.servlet.dao.EmailDao;
import com.bit.servlet.dao.EmailDaoOrclImpl;
import com.bit.servlet.dao.EmailVo;

//@WebServlet("/el")	// 현재 클래스를  /el  url 패턴에 반응하는 서블릿으로 등록
//	web.xml. 에 <Servlet>과 <Servlet -mapping> 을 등록한것과 동일
@WebServlet(name="Emaillist", urlPatterns="/el")
public class EmaillistServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//	View 처리를 위한 RequestDispatcher를 확인
			//	자신이 처리하고 있던 요청 객체와 응답 객체를
			//	다른 서블릿(JSP)로 전달하여 추가 작업진행
		String action = req.getParameter("a");		// a 파라미터를 확인
		
		if("form".equals(action)) {			// 	a=form 이면
			// 리스트
			// 등록 폼 : 파라미터에서 a 를 확인 분기
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/form.jsp");
		rd.forward(req, resp);
		
		}else if("delete".equals(action)) {
			Long no = Long.valueOf(req.getParameter("no"));
			
			EmailDao dao = new EmailDaoOrclImpl();
			dao.delete(no);
			
			resp.sendRedirect(req.getContextPath() + "/el");
			
		}	else {
//			리스트를 불러와 req에 attribute로 추가
		
			EmailDao dao = new EmailDaoOrclImpl();
//			전달해줄 객체 생성
			List<EmailVo> list = dao.getList();
			
//			요청 객체에 속성으로 추가
			req.setAttribute("list", list);		// 전달받은 서블릿은 요청으로부터 이 속성을 꺼내 쓸 수 있다.
			
			//	Dispactcher 확보
			RequestDispatcher rd = 
					getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/index.jsp");
//			요청과 응답 객체 전달
			rd.forward(req, resp);
		}
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");		// 히든필드
		
		if ("insert".equals(action)) {					//	action이 insert -> 삽입
			String firstName = req.getParameter("first_name");
			String lastName = req.getParameter("last_name");
			String email = req.getParameter("email");
			
			EmailVo vo = new EmailVo();
			vo.setLastName(lastName);
			vo.setFirstName(firstName);
			vo.setEmail(email);
			
			EmailDao dao = new EmailDaoOrclImpl();
			
			dao.insert(vo);
			
			// 리스트 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/el");
		
		}	else {
				doGet(req,resp);
		}
	}
	
	
	
	
}
