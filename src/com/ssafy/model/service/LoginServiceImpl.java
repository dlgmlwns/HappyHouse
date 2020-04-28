package com.ssafy.model.service;

import java.sql.Connection;
import java.sql.SQLException;


import com.ssafy.model.UserDto;
import com.ssafy.model.dao.LoginDao;
import com.ssafy.model.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {

	LoginDao loginDao;
	
	public LoginServiceImpl() {
		loginDao = new LoginDaoImpl();
	}
	
	@Override
	public UserDto login(String id, String password) throws Exception {
		if(id == null || password == null)
			return null;
		return loginDao.login(id, password);
	}

	@Override
	public int register(String id, String pw, String name, String address, String phone) throws SQLException {
		UserDto user = null;
		if(id == null || pw == null ||name == null || address == null||phone == null)
			return -1;
		else
			return loginDao.insert(new UserDto(id, pw, name, address, phone));
	}

	@Override
	public int updateInfo(String id, String pw, String name, String address, String phone) {
		return loginDao.update(id, pw, name, address, phone);
	}

	@Override
	public int deleteInfo(String id, String pw) {
		return loginDao.delete(id, pw);
	}

	@Override
	public UserDto showInfo(String id) throws Exception  {
		return loginDao.selectById(id);
	}

}
