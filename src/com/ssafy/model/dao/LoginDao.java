package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import com.ssafy.model.UserDto;


public interface LoginDao {

	public UserDto login(String id, String password) throws SQLException;
	/*UserInfo db에 새로운 유저 정보 입력 */
	public int insert(UserDto user) throws SQLException;
	public int update(String id, String pw, String name, String address, String phone);
	public int delete(String id,String pw);
	public UserDto selectById(String id) throws Exception;
	
	
	
}
