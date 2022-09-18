package co.bravo.myLife.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bravo.myLife.Main;
import co.bravo.myLife.common.Command;
import co.bravo.myLife.member.command.AjaxMemberIdCheck;
import co.bravo.myLife.member.command.MemberDelete;
import co.bravo.myLife.member.command.MemberInsert;
import co.bravo.myLife.member.command.MemberJoinForm;
import co.bravo.myLife.member.command.MemberSelect;
import co.bravo.myLife.member.command.MemberSelectList;
import co.bravo.myLife.member.command.MemberUpdate;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HashMap<String, Command> map = new HashMap<String, Command>();
	
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new Main());
		map.put("/memberSelectList.do", new MemberSelectList()); // 멤버 목록 보기 
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 멤버 입력 폼 호출 
		map.put("/memberSelect.do", new MemberSelect()); // 멤버 한명 상세 보기 
		map.put("/memberDelete.do", new MemberDelete()); // 멤버 삭제 하기 
		map.put("/memberUpdate.do", new MemberUpdate()); // 멤버 정보 변경 
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());
		map.put("/memberInsert.do", new MemberInsert()); // 멤버 추가
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if (!viewPage.endsWith(".do")) {
			
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().append(viewPage.substring(5));
			} else {
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage);
		}
	}

}
