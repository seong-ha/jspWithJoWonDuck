package co.micol.prj.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 실행할 커멘드 인터페이스
public interface Command {
	String exec(HttpServletRequest request, HttpServletResponse response);
}
