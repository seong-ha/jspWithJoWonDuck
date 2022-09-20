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

public class NoticeEdit implements Command {
	private String saveFolder = "C:\\fileUploadTest"; // 실제 파일을 저장할 공간
	private String charactSet = "utf-8";  // 전송되는 문자열 한글깨짐 방지(문자열 인코딩타입)
	private int maxSize = 1024*1024*1024; // 업로드할 파일 최대 사이즈
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 수정
		String viewPage = "notice/noticeError";
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		
		try {
		MultipartRequest multi = 
				new MultipartRequest(request, saveFolder, maxSize, charactSet, new DefaultFileRenamePolicy());
		String fileName = multi.getFilesystemName("file");
		String OriginalFileName = multi.getOriginalFileName(fileName);
		
		vo.setNoticeId(Integer.valueOf(multi.getParameter("noticeId")));
		vo.setNoticeWriter(multi.getParameter("noticeWriter"));
		vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
		vo.setNoticeTitle(multi.getParameter("noticeTitle"));
		vo.setNoticeSubject(multi.getParameter("noticeSubject"));

		vo.setNoticeAttech(OriginalFileName);
		vo.setNoticeAttechDirectory(saveFolder + File.separator + fileName);
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("id: " +  vo.getNoticeId());
		System.out.println("writer: " +  vo.getNoticeWriter());
		System.out.println("date: " +  vo.getNoticeDate());
		System.out.println("title: " +  vo.getNoticeTitle());
		System.out.println("subject: " +  vo.getNoticeSubject());
		System.out.println("attech: " +  vo.getNoticeAttech());
		System.out.println("directory: " +  vo.getNoticeAttechDirectory());
		
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
