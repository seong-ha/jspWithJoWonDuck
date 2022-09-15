package co.micol.prj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.member.common.Command;

public class MainCommand implements Command {

	// 처음 시작 하는 페이지를 돌려준다.
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "main/main";
	}

}
