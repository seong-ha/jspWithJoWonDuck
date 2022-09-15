package co.micol.prj.member.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.member.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버 아이디 중복 확인
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("id"));
		MemberVO memberVO = dao.memberSelect(vo);  // 검색 결과 얻기
		
		// vo의 null여부에 따라서 다른 값을 부여해서 들고 내려가고 싶은데 어디에 담아서 내려가야할지 모르겠다.
		if (memberVO == null) {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("fail");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "member/memberJoinForm";
	}

}
