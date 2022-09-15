package co.micol.prj.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.member.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 목록 보기
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> members = dao.memberSelectList();
		request.setAttribute("members", members);  // request 객체에다가 결과를 담는다.
		return "member/memberList";
	}

}
