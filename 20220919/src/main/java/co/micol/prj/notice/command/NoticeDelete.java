package co.micol.prj.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("id")));
		
		String viewPage = "notice/noticeError";
		
		int result = dao.noticeDelete(vo);
		if (result != 0) {
			viewPage = "noticeSelectList.do";
		} else {
			request.setAttribute("message", "게시글 삭제에 실패했습니다.");
		}

		return viewPage;
	}

}
