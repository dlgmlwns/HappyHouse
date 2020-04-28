package com.ssafy.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.model.HouseDealDto;
import com.ssafy.model.service.HouseDealService;
import com.ssafy.util.PageNavigation;

@WebServlet("/housedeal.do")
public class HouseDealController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HouseDealController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		
		System.out.println(act);
		if("list".equals(act)) {
			search(request,response);
		}
		else if("search".equals(act)) {
			search(request, response);
		}
		else if("detail".equals(act)) {
			detail(request, response);
		}
	}
	
//	private void list(HttpServletRequest request, HttpServletResponse response) {
//		Collection<HouseDealDto> housedeals = null;
//		try {
//			String by = request.getParameter("by");
//			String keyword = request.getParameter("keyword");
//			String type = request.getParameter("type");
//			housedeals = HouseDealService.getService().select(by, keyword, type);
//			request.setAttribute("housedeals", housedeals);
//			RequestDispatcher disp = request.getRequestDispatcher("/housedeallist.jsp");
//			disp.forward(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) {
		Collection<HouseDealDto> housedeals = null;
		try {
			String orderby = request.getParameter("orderby");
			String by = request.getParameter("by");
			String keyword = request.getParameter("keyword");
			String[] type = request.getParameterValues("type");
			String cp = request.getParameter("pg");
			int currentPage = cp == null || cp.equals("") ? 1 : Integer.parseInt(cp);
			String spp = request.getParameter("spp");
			int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
			housedeals = HouseDealService.getService().select(currentPage, sizePerPage, orderby, by, keyword, type);
			PageNavigation pageNavigation = HouseDealService.getService().makePageNavigation(currentPage, sizePerPage, by, keyword, type);
			request.setAttribute("housedeals", housedeals);
			request.setAttribute("navigation", pageNavigation);
			RequestDispatcher disp = request.getRequestDispatcher("/housedeallist.jsp");
			disp.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void detail (HttpServletRequest request, HttpServletResponse response) {
		HouseDealDto housedeal = null;
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			housedeal = HouseDealService.getService().selectByNo(no);
			request.setAttribute("housedeal", housedeal);
			RequestDispatcher disp = request.getRequestDispatcher("/housedealdetail.jsp");
			disp.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
