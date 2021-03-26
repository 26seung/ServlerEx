package com.bit.servlet.dao;

import java.sql.*;

public class UserDaoOrclImpl implements UserDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn =null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@220.85.164.169:1521:xe";
			conn = DriverManager.getConnection(dburl,"SEUNG","1234");
	
		}	 catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		
		return conn;
	}

	@Override
	public int insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO users"
					+ "(no, name, password, email, gender) "
					+ "VALUES(seq_users_pk.NEXTVAL, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(1, vo.getGender());
			
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
	public UserVo getUserByEmailAndPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT no, name, password, email, gender, create_at FROM users "
					+ "WHERE email=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			
			// 쿼리 수행
			rs = pstmt.executeQuery();
			
			// 만약 레코드가 있다면 -> 사용자가 있다는 뜻
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String pwd = rs.getString(3);
				String eml = rs.getString(4);
				String gender = rs.getString(5);
				Date createAt = rs.getDate(6);
				
				// 사용자 객체 생성
				vo = new UserVo(no,name,pwd,eml,gender,createAt);
				
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}	catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

}
