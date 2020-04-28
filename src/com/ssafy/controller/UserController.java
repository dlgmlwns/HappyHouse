package com.ssafy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.Joinable;

import com.mysql.cj.Session;
import com.ssafy.model.UserDto;
import com.ssafy.model.service.LoginService;
import com.ssafy.model.service.LoginServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService;
           
    @Override
    public void init() throws ServletException {
    	super.init();
    	loginService = new LoginServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();		
		String act = request.getParameter("act");
		
		if("mvlogin".equals(act)) {
			response.sendRedirect(root + "/user/login.jsp");
		} else if("mvjoin".equals(act)) {
			response.sendRedirect(root + "/user/join.jsp");
		} else if("login".equals(act)) {
			login(request, response);
		} else if("logout".equals(act)) {
			logout(request, response);
		} else if("join".equals(act)) {
			join(request,response);
		}else if("userinfo".equals(act)) {
			search(request,response);
		}else if("index".equals(act)) {
			response.sendRedirect(root + "/index.jsp");
		}else if("update".equals(act)) {
			update(request,response);
		}else if("mvupdate".equals(act)) {
			response.sendRedirect(root + "/user/userinfoupdate.jsp");
		}else if("delete".equals(act)) {
			delete(request,response);
		}else if("findpw".equals(act)) {
			findpw(request,response);
		}else if("mvfindpw".equals(act)) {
			response.sendRedirect(root + "/user/findpw.jsp");
		}
	}
	
	private void findpw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="/user/findpw.jsp";
		String id =request.getParameter("id");
		String name = request.getParameter("name");
		
		try {
			UserDto userDto = loginService.showInfo(id);
			if (userDto!=null) {		
				request.setAttribute("password", userDto.getPassword());	
			}else {
				request.setAttribute("pwmsg", "아이디,이름 기입 후 시도해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "비밀번호 찾는 과정 중 문제가 발생했습니다.");	
		}		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="/index.jsp";
		String id =request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println(id+" "+password);
		try {
			int result = loginService.deleteInfo(id, password);
			if (result==1) {
				HttpSession session = request.getSession();
				session.invalidate();
				request.setAttribute("msg", "탈퇴가 완료되었습니다.");
				
			}else {
				request.setAttribute("msg", "회원탈퇴 중 문제가 발생했습니다.");
				path = "uesr/userinfoupdate.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원탈퇴 중 문제가 발생했습니다.");
			path = "uesr/userinfoupdate.jsp";		
		}		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	      UserDto userDto = null;
	      String id =request.getParameter("id");
	      System.out.println(id);
		try {
			
				userDto= loginService.showInfo(id);
				
				if (userDto!=null) {
					request.setAttribute("userdetail",userDto);
		            RequestDispatcher disp = request.getRequestDispatcher("user/userinfo.jsp");
		            disp.forward(request, response);
				}else {
					request.setAttribute("msg", "없는 아이디 입니다.");
				}
	        
	        } catch (Exception e) {
	        	e.printStackTrace();
				request.setAttribute("msg", "회원정보 불러오는 중에 문제가 발생했습니다.");
	        }
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="/user/userinfoupdate.jsp";
		String id =request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		try {
			int result = loginService.updateInfo(id, password, name, address, phone);
			if (result==1) {		
				HttpSession session =request.getSession();
				session.setAttribute("userinfo", new UserDto(id, password, name, address, phone));
				request.setAttribute("msg", "회원정보수정이 완료되었습니다.");				
			}else {
				request.setAttribute("msg", "아이디,비밀번호,이름 기입 확인 후 다시 시도해주세요.");
				path = "user/userinfoupdate.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보수정 중 문제가 발생했습니다.");
			path = "user/userinfoupdate.jsp";		
		}		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="/index.jsp";
		String id =request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		try {
			int result = loginService.register(id, password, name, address, phone);
			if (result==1) {
				request.setAttribute("msg", "회원가입이 완료되었습니다.");				
			}else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인해주세요.");
				path = "user/join.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 문제가 발생했습니다.");
			path = "user/join.jsp";		
		}		
		request.getRequestDispatcher(path).forward(request, response);
	}


	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path ="/index.jsp";
		String id =request.getParameter("id");
		String password = request.getParameter("password");
		
		try {
			UserDto userDto = loginService.login(id, password);
			if (userDto!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", userDto);				
			}else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인해주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}


}
