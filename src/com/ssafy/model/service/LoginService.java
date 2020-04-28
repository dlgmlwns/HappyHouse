package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.model.UserDto;

public interface LoginService {

	public UserDto login(String id, String password) throws Exception;
	public int register(String id,String pw, String name, String address,String phone) throws SQLException;
	public int updateInfo(String id, String pw,String name, String address, String phone);
	public int deleteInfo(String id, String pw);
	public UserDto showInfo(String id) throws Exception;
	
}
