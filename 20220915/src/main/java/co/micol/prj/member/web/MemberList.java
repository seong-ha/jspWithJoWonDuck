package co.micol.prj.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 수행할 코드를 적는 곳(member list 가져오기
		response.setCharacterEncoding("utf-8");
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> members = dao.memberSelectList();
		request.setAttribute("members", members);  // request 객체에다가 결과를 담는다.
		String viewPage = "/WEB-INF/views/member/memberList.jsp";  // 결과를 돌려줄 페이지

		// 중요한 것. sendRedirect는 그냥 새 페이지 열때 요청하는거.
		// RequestDipatcher는 어떠한 데이터를 가지고 있는 request객체를 주면서 다른 곳에 새로 요청. 그래서 새로운 request객체 생성하면서 그것도 포함시킨다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
