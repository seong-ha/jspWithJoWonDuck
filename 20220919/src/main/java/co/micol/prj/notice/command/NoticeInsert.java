package co.micol.prj.notice.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeInsert implements Command {
	private String saveFolder = "C:\\fileUploadTest"; // 실제 파일을 저장할 공간
	private String charactSet = "utf-8";  // 전송되는 문자열 한글깨짐 방지(문자열 인코딩타입)
	private int maxSize = 1024*1024*1024; // 업로드할 파일 최대 사이즈
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 등록
		String viewPage = "notice/noticeError";
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		
		try {
			MultipartRequest multi =
					new MultipartRequest(request, saveFolder, maxSize, charactSet, new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("ufile"); // 물리적 위치에 파일저장.  여기의 file이라는 것은 noticeWriteForm.jsp 의 id,name값.
			String originalFileName = multi.getOriginalFileName("ufile"); // 실제파일명
			
			vo.setNoticeWriter(multi.getParameter("noticeWriter"));
			vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate"))); // 문자를 java Date객체로 변환
			vo.setNoticeTitle(multi.getParameter("noticeTitle"));
			vo.setNoticeSubject(multi.getParameter("noticeSubject"));
			
			vo.setNoticeAttech(originalFileName);
			vo.setNoticeAttechDirectory(saveFolder + File.separator + fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = dao.noticeInsert(vo);
		
		
		if (result != 0) {
			viewPage = "noticeSelectList.do";
		} else {
			request.setAttribute("message", "게시글 등록에 실패했습니다.");
		}
		
		return viewPage;
	}

}
