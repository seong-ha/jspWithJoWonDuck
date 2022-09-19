package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;

public class MemberLogout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		// java로 하는거.
		// 세션객체에 있는(내가 담아준) 이름 가져오기. 잘가라고 인사하려고
		String name = (String) session.getAttribute("name");
		session.invalidate(); // 서버가 만들어준 세션객체 삭제. 다음 접속할 때는 새로 만들어야 되게 됨.
		request.setAttribute("message", name + "님 정상적으로 로그아웃 되었습니다.");
		return "member/memberMessage";
	}

}
