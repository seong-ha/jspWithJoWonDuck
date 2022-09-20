package co.micol.prj.notice.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeEdit implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 수정
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("noticeId")));
		vo.setNoticeWriter(request.getParameter("noticeWriter"));
		vo.setNoticeDate(Date.valueOf(request.getParameter("noticeDate")));
		vo.setNoticeTitle(request.getParameter("noticeTitle"));
		vo.setNoticeSubject(request.getParameter("noticeSubject"));
		
		// 파일 첨부 처리하고
		// vo.setNoticeAttech(request.getParameter("noticeAttech"));
		
		String viewPage = "notice/noticeError";
		int result = dao.noticeUpdate(vo);
		
		if (result != 0) {
			NoticeVO resultVO = dao.noticeSelect(vo);
			request.setAttribute("vo", resultVO);
			viewPage = "notice/noticeSelect"; // 수정 완료시 목록으로 돌아감
		} else {
			request.setAttribute("message", "게시글 수정에 실패했습니다.");
		}
		
		return viewPage;
	}

}
