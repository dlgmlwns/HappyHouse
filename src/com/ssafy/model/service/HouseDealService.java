package com.ssafy.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.model.HouseDealDto;
import com.ssafy.model.dao.HouseDealDao;
import com.ssafy.util.DBUtil;
import com.ssafy.util.PageNavigation;

public class HouseDealService {
	private static HouseDealService service = new HouseDealService();
	private static HouseDealDao dao = HouseDealDao.getDao();
	
	public static HouseDealService getService() {
		return service;
	}
	
	private HouseDealService() {}
	
//	public Collection<HouseDealDto> list() throws SQLException{
//		Connection con = null;
//		List<HouseDealDto> list = null;
//		try {
//			con = DBUtil.getConnection();
//			list = dao.selectAll(con);
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			DBUtil.close(con);
//		}
//		return list;
//	}
	
	public Collection<HouseDealDto> select(int currentPage, int sizePerPage, String orderby, String by, String keyword, String[] typein) throws SQLException{
		Connection con = null;
		List<HouseDealDto> alllist = null;
		List<HouseDealDto> list = new LinkedList<>();
		try {
            con = DBUtil.getConnection();
            alllist = dao.search(con, by, keyword, typein);
            if(by != null) {
            	for(HouseDealDto housedeal : alllist) {
                	if(by.equals("name")) {
                		if(housedeal.getAptName().contains(keyword)) {
                			list.add(housedeal);
                		}
                	}
                	else if(by.equals("dong")) {
                		if(housedeal.getDong().equals(keyword)) {
                			list.add(housedeal);
                		}
                	}
                	else {
                		String dealAmount = housedeal.getDealAmount().trim();
                		int price = Integer.parseInt(dealAmount.replace(",", ""));
                		if(price < Integer.parseInt(keyword)) {
                			list.add(housedeal);
                		}
                	}
                }
            }
            else {
            	list = alllist;
            }
        } catch (SQLException e) {
            // e.printStackTrace();// 로그
            throw e; // 전파 - controller
        } finally {
            DBUtil.close(con);
        }
		
		if(orderby != null) {
			if(orderby.equals("no")) {
				list.sort(new Comparator<HouseDealDto>() {
					@Override
					public int compare(HouseDealDto o1, HouseDealDto o2) {
						
						return o1.getNo() - o2.getNo();
					}
				});
			}
			else if(orderby.equals("name")) {
				list.sort(new Comparator<HouseDealDto>() {
					@Override
					public int compare(HouseDealDto o1, HouseDealDto o2) {
						
						return o1.getAptName().compareTo(o2.getAptName());
					}
				});
			}
			else {
				list.sort(new Comparator<HouseDealDto>() {
					@Override
					public int compare(HouseDealDto o1, HouseDealDto o2) {
						String o1dealAmount = o1.getDealAmount().trim();
						String o2dealAmount = o2.getDealAmount().trim();
						int o1price = Integer.parseInt(o1dealAmount.replace(",", ""));
						int o2price = Integer.parseInt(o2dealAmount.replace(",", ""));
						return o1price - o2price;
					}
				});
			}
		}
		else {
			list.sort(new Comparator<HouseDealDto>() {
				@Override
				public int compare(HouseDealDto o1, HouseDealDto o2) {
					return o1.getNo() - o2.getNo();
				}
			});
		}
		List<HouseDealDto> returnlist = new LinkedList<>();
		int last = list.size() > currentPage*sizePerPage ? currentPage*sizePerPage : list.size();
		
		for(int i = (currentPage-1)*10; i < last; i++) {
			returnlist.add(list.get(i));
		}
		
        return returnlist;
	}
	
	public HouseDealDto selectByNo(int no) throws SQLException{
		HouseDealDto housedeal = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			housedeal = dao.selectByNo(con, no);
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.close(con);
		}
		return housedeal;
	}
	
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String by, String keyword, String[] typein) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = dao.getTotalCount(by, keyword, typein); //총 게시글 수
		System.out.println(totalCount);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount+9)/sizePerPage; //총 페이지 수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange true : 이전 X    false : 이전 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = currentPage > ((totalPageCount-1)/naviSize)*naviSize; 
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
}
