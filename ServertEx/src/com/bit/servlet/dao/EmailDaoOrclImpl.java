package com.bit.servlet.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EmailDaoOrclImpl implements EmailDao {

	// 커넥션 메서드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String dburl = "jdbc:oracle:thin:@220.85.164.169:1521:xe";
			// 드라이버 매니저  -> 접속 얻어오기
			conn = DriverManager.getConnection(dburl,"SEUNG","1234");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		return conn;
	}
	@Override
	public List<EmailVo> getList() {
		List<EmailVo>list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT no, first_name, last_name, email, create_at " +
					"FROM emaillist ORDER BY create_at DESC";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				Date createAt = rs.getDate(5); 			// java.utill.Date    자바기반이기에 자바 임포트
				
				EmailVo vo = new EmailVo(no, lastName, firstName, email, createAt);
				// 리스트에 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			// 자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public int insert(EmailVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO emaillist"
					+ "(no, last_name, first_name, email) "
					+ "VALUES(seq_emaillist_pk.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
			insertedCount = pstmt.executeUpdate();
			
		}	catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				pstmt.close();
				conn.close();
			}	catch (Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount;
	}

	@Override
	public int delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM emaillist "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			// 쿼리 수행
			deletedCount = pstmt.executeUpdate();
			
		}	catch (SQLException e) {
			e.printStackTrace();
			
		}	finally {
			try {
				pstmt.close();
				conn.close();
			}	catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}

}
