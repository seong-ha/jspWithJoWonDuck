package co.bravo.myLife.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bravo.myLife.common.Command;
import co.bravo.myLife.member.service.MemberService;
import co.bravo.myLife.member.service.MemberServiceImpl;
import co.bravo.myLife.member.service.MemberVO;

public class MemberDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 상세 정보 수정하기
		
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));

		dao.memberDelete(vo);
		
		return "memberSelectList.do";
	}

}
