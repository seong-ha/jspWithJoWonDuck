package co.micol.prj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.DAO;

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PreparedStatement pstmt;  // DBMS에 명령을 보내고
    private ResultSet rs;  // 실행된 결과를 돌려받을때(Select 구문에만 가능)
	
    public FirstServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO(); // 데이터 베이스 연결을 확인한다.
		String sql = "select * from member";
		try {
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("member_id"));
				System.out.println(rs.getString("member_password"));
				System.out.println(rs.getString("member_name"));
				System.out.println(rs.getString("member_tel"));
				System.out.println(rs.getString("member_author"));
				System.out.println("---------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();  // html 문서를 만드는 메소드
		String name = request.getParameter("name");
		
		out.print("넘어온 아이디 값: ");
		out.print(request.getParameter("id"));
		if (!name.isEmpty()) {
			out.print("넘어온 이름: ");
			out.print(name);
		}
		out.print(", 넘어온 비밀번호 값: ");
		out.print(request.getParameter("password"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}