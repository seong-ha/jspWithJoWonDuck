package co.micol.prj.member.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.member.common.DataSource;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;

public class MemberServiceImpl implements MemberService {

	private DataSource dao = new DataSource(); // 데이터 연결 객체 생성
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {
		// 전체 멤버 목록 가져오기
		List<MemberVO> list = new ArrayList<>();
		MemberVO vo = null;
		String sql = "SELECT * FROM MEMBER";

		try {
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new MemberVO();

				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberAuthor(rs.getString("member_author"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// 한명의 멤버를 조회한다.
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?)";
		int result = 0;
		
		try {
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberId());
			pstmt.setString(2, vo.getMemberPassword());
			pstmt.setString(3, vo.getMemberName());
			pstmt.setString(4, vo.getMemberTel());
			pstmt.setString(5, vo.getMemberAuthor());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 한 명의 데이터 변경. 아이디를 제외한 모든 값이 변경 가능함.
		String sql = "UPDATE MEMBER"
				+ " SET MEMBER_PASSWORD = ?, MEMBER_NAME = ?, MEMBER_TEL = ?, MEMBER_AUTHOR = ? WHERE MEMBER_ID = ?";
		int result = 0;
		
		try {
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberPassword());
			pstmt.setString(2, vo.getMemberName());
			pstmt.setString(3, vo.getMemberTel());
			pstmt.setString(4, vo.getMemberAuthor());
			pstmt.setString(5, vo.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		String sql = "DELETE MEMBER WHERE MEMBER_ID = ?";
		int result = 0;
		
		try {
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		// 아이디 중복 체크. 존재하면 false 반환
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			close();
		}
		
		return true;
	}

	public void close() {
		try {
			
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (dao.conn != null) {
				dao.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
