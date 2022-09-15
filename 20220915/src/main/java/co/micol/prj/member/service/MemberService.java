package co.micol.prj.member.service;

import java.util.List;

public interface MemberService {
	List<MemberVO> memberSelectList();  // 전체 목록 가져오기
	MemberVO memberSelect(MemberVO vo);  // 한명의 데이터 가져오기
	int memberInsert(MemberVO vo);  // 데이터 추가
	int memberUpdate(MemberVO vo);  // 데이터 갱신
	int memberDelete(MemberVO vo); // 데이터 삭제
	
	boolean isMemberIdCheck(String id);  // 아이디 중복 체크 (존재하면 false, 미존재 true)
}
