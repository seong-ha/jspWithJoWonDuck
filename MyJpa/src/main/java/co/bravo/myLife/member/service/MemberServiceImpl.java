package co.bravo.myLife.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.bravo.myLife.common.DataSource;
import co.bravo.myLife.mybatis.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService {
	private SqlSession sqlSession = DataSource.getSession().openSession(true);
	private MemberMapper map = sqlSession.getMapper(MemberMapper.class);
	
	@Override
	public List<MemberVO> memberSelectList() {
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return map.memberDelete(vo);
	}

	@Override
	public boolean isIdCheck(String id) {
		return map.isIdCheck(id);
	}

}
