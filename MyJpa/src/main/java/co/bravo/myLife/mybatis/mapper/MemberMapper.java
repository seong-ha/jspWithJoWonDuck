package co.bravo.myLife.mybatis.mapper;

import java.util.List;

import co.bravo.myLife.member.service.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);  // 한 명 조회
	int memberInsert(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	
	boolean isIdCheck(String id);  // 아이디 중복 체크
}
