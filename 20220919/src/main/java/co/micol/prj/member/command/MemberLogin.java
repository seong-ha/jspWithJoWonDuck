package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 세션을 불러온다.
		
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		
		MemberVO resultVO = dao.memberSelect(vo);
		
		if (resultVO != null) {
			// 성공하면 세션처리 하고
			session.setAttribute("id", resultVO.getMemberId()); // 세션 객체에 아이디 담고
			session.setAttribute("author", resultVO.getMemberAuthor());  // 세션 객체에 권한 담고
			session.setAttribute("name", resultVO.getMemberName());  // 세션 객체에 이름 담고

			request.setAttribute("message", resultVO.getMemberName() + "님 환영합니다.");
		} else {
			// 실패메세지 전달
			request.setAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		return "member/memberMessage";
	}

}
