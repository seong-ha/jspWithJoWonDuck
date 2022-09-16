package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.Main;
import co.micol.prj.command.MemberDelete;
import co.micol.prj.command.MemberJoinForm;
import co.micol.prj.command.MemberSelect;
import co.micol.prj.command.MemberUpdate;
import co.micol.prj.common.Command;
import co.micol.prj.member.command.MemberSelectList;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	// 모든 요청과 명령을 매핑하는 곳
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new Main()); // 처음 오는 페이지를 돌려준다.
		map.put("/memberSelectList.do", new MemberSelectList()); // 멤버 목록 보기 
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 멤버 입력 폼 호출 
		map.put("/memberSelect.do", new MemberSelect()); // 멤버 한명 상세 보기 
		map.put("/memberDelete.do", new MemberDelete()); // 멤버 삭제 하기 
		map.put("/memberUpdate.do", new MemberUpdate()); // 멤버 정보 변경 
	}

	// 요청을 분석하고, 실행하고 , 결과를 돌려주는 곳
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 요청 분석
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		// 요청 실행
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		//view Resolver  결과 돌려주기
		if (!viewPage.endsWith(".do")) {
			
			if (viewPage.startsWith("ajax:")) {
				// ajax
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else {
				viewPage = "WEB-INF/views/" + viewPage + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage);
		}

	}

}
