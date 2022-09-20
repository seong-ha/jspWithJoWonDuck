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
import co.micol.prj.common.Command;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;
import co.micol.prj.notice.command.NoticeDelete;
import co.micol.prj.notice.command.NoticeEdit;
import co.micol.prj.notice.command.NoticeEditForm;
import co.micol.prj.notice.command.NoticeInsert;
import co.micol.prj.notice.command.NoticeSelect;
import co.micol.prj.notice.command.NoticeSelectList;
import co.micol.prj.notice.command.NoticeWriteForm;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
	
    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new Main());
		map.put("/noticeSelectList.do", new NoticeSelectList());
		map.put("/noticeWriteForm.do", new NoticeWriteForm());
		map.put("/noticeInsert.do", new NoticeInsert());
		map.put("/noticeSelect.do", new NoticeSelect()); // 공지사항 상세보기
		map.put("/noticeEditForm.do", new NoticeEditForm()); // 상세보기에서 게시글 수정 폼으로
		map.put("/noticeEdit.do", new NoticeEdit()); // 상세보기에서 게시글 수정
		map.put("/noticeDelete.do", new NoticeDelete()); // 상세보기에서 게시글 삭제
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin()); // 로그인 처리
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃 처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if (!viewPage.endsWith(".do")) {
			
			if (viewPage.startsWith("ajax:")) { // ajax를 사용할때 
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else {
				// tiles 적용 안 태울때
				if (viewPage .startsWith("no:")) {
					viewPage = "/WEB-INF/views/" + viewPage.substring(3) + ".jsp";
				} else { // tiles  적용태울때
					viewPage = viewPage + ".tiles";  // tiles layout 사용
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage); // .do return
		}
	}

}
