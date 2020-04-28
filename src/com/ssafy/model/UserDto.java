package com.ssafy.model;

public class UserDto {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	
	

	public UserDto() {
		super();
	}

	public UserDto(String id, String password, String name, String address, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "아이디 = " + id + "<br>비밀번호 = " + password + "<br>이름 = " + name + "<br>주소 = " + address + "<br>전화번호 = "
				+ phone;
	}

}
