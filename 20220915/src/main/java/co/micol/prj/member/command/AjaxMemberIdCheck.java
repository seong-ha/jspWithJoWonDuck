package co.micol.prj.member.command;

import java.io.IOException;

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
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("id"));
		vo = dao.memberSelect(vo);  // 검색 결과 얻기
		
		// vo의 null여부에 따라서 다른 값을 부여해서 들고 내려가고 싶은데 어디에 담아서 내려가야할지 모르겠다.
		if (vo != null) {
			request.setAttribute("check", "1");  // 페이지에 전달하기 위해서 담아준다.
			try {
				response.getWriter().print("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("check", "0");
			try {
				response.getWriter().print("fail");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "member/memberJoinForm";
	}

}
