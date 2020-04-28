package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.model.UserDto;
import com.ssafy.util.DBUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public UserDto login(String id, String password) throws SQLException {
		UserDto userDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, name, address,phone \n");
			sql.append("from userinfo \n");
			sql.append("where id = ? and password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userDto = new UserDto();
				userDto.setId(rs.getString("id"));
				userDto.setPassword(rs.getString("password"));
				userDto.setName(rs.getString("name"));
				userDto.setAddress(rs.getString("address"));
				userDto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			userDto = null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return userDto;
	}

	
	
	@Override
	public int insert(UserDto user) throws SQLException {
		PreparedStatement pstmt=null;
		Connection conn = null;
		int result=0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into UserInfo(id,password,name,address,phone) values(?,?,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getPhone());
			result=pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return result;		
	}

	@Override
	public int update(String id, String pw, String name, String address, String phone) {
		PreparedStatement pstmt=null;
		Connection conn = null;
		int result=0;
		try {
			conn = DBUtil.getConnection();
			String sql = "update UserInfo set name=?,address=?,phone=? where id=? and password=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			pstmt.setString(5, pw);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return result;	
	}

	@Override
	public int delete(String id, String pw) {
		PreparedStatement pstmt=null;
		Connection conn = null;
		int result=0;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from UserInfo where id =? and password=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return result;	
	}



	@Override
	public UserDto selectById(String id) throws Exception {
		String sql ="select id,password,name,address,phone from UserInfo where id=?";
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		UserDto userDto=null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String userid = rset.getString(1);
				String password =rset.getString(2);
				String name = rset.getString(3);
				String address= rset.getString(4);
				String phone = rset.getString(5);
				userDto =new UserDto();
				userDto.setId(userid);
				userDto.setPassword(password);
				userDto.setName(name);
				userDto.setAddress(address);
				userDto.setPhone(phone);
			}
		} catch (Exception e) {
			e.printStackTrace();//로깅용
			throw e;
		}finally {
			DBUtil.close(rset);
			DBUtil.close(pstmt);
			DBUtil.close(conn);			
		}
		return userDto;
	}

}
