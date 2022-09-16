package co.micol.prj;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberInsert;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberSelect;
import co.micol.prj.member.command.MemberSelectList;
import co.micol.prj.member.common.Command;

/*
 * Servlet Implementation class FrontController
 * 모든 .do 요청을 분석하고 처리한다.
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>(); // 요청한 값을 저장하기 위해서

	public FrontController() {
		super();
	}

	// 모든 요청을 등록하는 곳
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand()); // 처음 시작하는 페이지
		map.put("/memberSelectList.do", new MemberSelectList()); // 멤버목록 보기
		map.put("/memberSelect.do", new MemberSelect()); // 멤버 상세 정보
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 멤버 입력 화면
		map.put("/memberInsert.do", new MemberInsert()); // 멤버 입력 처리
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); // 멤버 아이디 중복 확인
	}

	// 요청을 분석, 처리하고 결과를 돌려주는 곳
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지
		String uri = request.getRequestURI(); // 도메인을 제외한 실제 요청정보
		String contextPath = request.getContextPath(); // contextPath 가져오기
		// 처리할 요청명 구함. "contextPath/실제요청"이니까 contextPath길이 이후부터 가져오는거
		String page = uri.substring(contextPath.length());
		System.out.println("url: " + request.getRequestURL());
		System.out.println("uri: " + uri);
		System.out.println("contextPath: " + contextPath);
		System.out.println("page: " + page);

		Command command = map.get(page); // 처리할 Command를 찾음
		String viewPage = command.exec(request, response); // Command를 실행하고 돌려줄 페이지를 받음

		// 전체 view Resolver
		if (!viewPage.endsWith(".do")) {
			
			if (viewPage.startsWith("ajax:")) {  // ajax를 처리하기 위한 view Resolver
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return; // 호출한 페이지로 돌려보내자.
			} else {  // 리턴 값이 보여줄 페이지를 가지고 올 때의 view Resolver
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp";

				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
			
		} else {
			response.sendRedirect(viewPage); // 리턴값이 *.do로 올 때 처리
		}
	}

}
