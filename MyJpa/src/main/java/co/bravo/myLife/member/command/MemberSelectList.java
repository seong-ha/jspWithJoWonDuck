package co.bravo.myLife.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.bravo.myLife.common.Command;
import co.bravo.myLife.member.service.MemberService;
import co.bravo.myLife.member.service.MemberServiceImpl;
import co.bravo.myLife.member.service.MemberVO;

public class MemberSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 목록 가져오기
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> list = new ArrayList<>();
		list = dao.memberSelectList();
		
		request.setAttribute("members", list);
		return "member/memberSelectList";
	}

}
