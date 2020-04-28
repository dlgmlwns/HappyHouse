package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.HouseDealDto;
import com.ssafy.util.DBUtil;

public class HouseDealDao {
	private static HouseDealDao dao = new HouseDealDao();
	
	public static HouseDealDao getDao() {
		return dao;
	}
	
	private HouseDealDao() {}
	
//	public List<HouseDealDto> selectAll(Connection con) throws SQLException{
//		List<HouseDealDto> housedeals = new ArrayList<HouseDealDto>();
//		String sql = "select * from housedeal order by no limit 10";
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		try {
//			pstmt = con.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//			while(rset.next()) {
//				int no = rset.getInt(1);
//				String dong = rset.getString(2);
//				String aptName = rset.getString(3);
//				String code = rset.getString(4);
//				String dealAmount = rset.getString(5);
//				String buildYear = rset.getString(6);
//				String dealYear = rset.getString(7);
//				String dealMonth = rset.getString(8);
//				String dealDay = rset.getString(9);
//				String area = rset.getString(10);
//				String floor = rset.getString(11);
//				String jibun = rset.getString(12);
//				String type = rset.getString(13);
//				String rentMoney = rset.getString(14);
//				
//				HouseDealDto housedeal = new HouseDealDto(no, dong, aptName, code, dealAmount, buildYear, dealYear, dealMonth, dealDay, area, floor, jibun, type, rentMoney);
//				housedeals.add(housedeal);
//			}
//		} finally {
//			DBUtil.close(rset);
//            DBUtil.close(pstmt);
//		}
//		return housedeals;
//	}
	
	public List<HouseDealDto> search(Connection con, String by, String keyword, String[] typein)throws SQLException {
		List<HouseDealDto> housedeals = new ArrayList<HouseDealDto>();
		StringBuilder sql = new StringBuilder("select * from housedeal ");
		sql.append(" where ");
//		if(by != null) {
//			if(by.equals("name")) {
//				sql.append("AptName like ? ");
//			}
//			else if(by.equals("dong")) {
//				sql.append("dong = ? ");
//			}
//			else {
//				sql.append("replace(dealAmount,',','')+0 < ? ");
//			}
//			sql.append(" and ");
//		}
		if(typein == null) {
			sql.append(" 1 = 1 ");
		}
		else {
			sql.append(" type in (");
			for(String s : typein) {
				sql.append(s + ",");
			}
			sql.append("0)");
		}
		System.out.println("검색 쿼리 확인: " + sql);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(sql.toString());
//			if(by != null) {
//				if(by.equals("name")) {
//					pstmt.setString(1, "%" + keyword + "%");
//				}
//				else if(by.equals("dong")) {
//					pstmt.setString(1, keyword);
//				}
//				else {
//					pstmt.setInt(1, Integer.parseInt(keyword));
//				}
//			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				int no = rset.getInt(1);
				String dong = rset.getString(2);
				String aptName = rset.getString(3);
				String code = rset.getString(4);
				String dealAmount = rset.getString(5);
				String buildYear = rset.getString(6);
				String dealYear = rset.getString(7);
				String dealMonth = rset.getString(8);
				String dealDay = rset.getString(9);
				String area = rset.getString(10);
				String floor = rset.getString(11);
				String jibun = rset.getString(12);
				String type = rset.getString(13);
				String rentMoney = rset.getString(14);
				String img = rset.getString(15);
				
				HouseDealDto housedeal = new HouseDealDto(no, dong, aptName, code, dealAmount, buildYear, dealYear, dealMonth, dealDay, area, floor, jibun, type, rentMoney, img);
				housedeals.add(housedeal);
			}
		} finally {
			DBUtil.close(rset);
            DBUtil.close(pstmt);
		}
		return housedeals;
	}
	
	public HouseDealDto selectByNo(Connection con, int noinput) throws SQLException {
		HouseDealDto housedeal = null;
		String sql = "select * from housedeal where no = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noinput);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				int no = rset.getInt(1);
				String dong = rset.getString(2);
				String aptName = rset.getString(3);
				String code = rset.getString(4);
				String dealAmount = rset.getString(5);
				String buildYear = rset.getString(6);
				String dealYear = rset.getString(7);
				String dealMonth = rset.getString(8);
				String dealDay = rset.getString(9);
				String area = rset.getString(10);
				String floor = rset.getString(11);
				String jibun = rset.getString(12);
				String type = rset.getString(13);
				String rentMoney = rset.getString(14);
				String img = rset.getString(15);
				
				housedeal = new HouseDealDto(no, dong, aptName, code, dealAmount, buildYear, dealYear, dealMonth, dealDay, area, floor, jibun, type, rentMoney, img);
			}
		} finally {
			DBUtil.close(rset);
            DBUtil.close(pstmt);
		}
		return housedeal;
	}

	public int getTotalCount(String by, String keyword, String[] typein) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder("select count(no) from housedeal ");
			sql.append(" where ");
			if(by != null) {
				if(by.equals("name")) {
					sql.append("AptName like ? ");
				}
				else if(by.equals("dong")) {
					sql.append("dong = ? ");
				}
				else {
					sql.append("replace(dealAmount,',','')+0 < ? ");
				}
				sql.append(" and ");
			}
			if(typein == null) {
				sql.append(" 1 = 1 ");
			}
			else {
				sql.append(" type in (");
				for(String s : typein) {
					sql.append(s + ",");
				}
				sql.append("0)");
			}
			pstmt = conn.prepareStatement(sql.toString());
			if(by != null) {
				if(by.equals("name")) {
					pstmt.setString(1, "%" + keyword + "%");
				}
				else if(by.equals("dong")) {
					pstmt.setString(1, keyword);
				}
				else {
					pstmt.setInt(1, Integer.parseInt(keyword));
				}
			}
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return cnt;
	}
}
