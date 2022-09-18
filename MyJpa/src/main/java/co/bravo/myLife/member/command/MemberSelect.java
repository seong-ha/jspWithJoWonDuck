package co.bravo.myLife.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bravo.myLife.common.Command;
import co.bravo.myLife.member.service.MemberService;
import co.bravo.myLife.member.service.MemberServiceImpl;
import co.bravo.myLife.member.service.MemberVO;

public class MemberSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 한명 상세 정보 가져오기
		
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		MemberVO memberVO = dao.memberSelect(vo);
		
		request.setAttribute("member", memberVO);
		return "member/memberSelect";
	}

}
