package com.g3.elis.dto.form;

public class UserDto 
{
	private String division;
	private String name;
	private String doorLogNo;
	private String dept;
	private String team;
	private String email;	
	private String status;
	private String password;
	private String gender;
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoorLogNo() {
		return doorLogNo;
	}
	public void setDoorLogNo(String doorLogNo) {
		this.doorLogNo = doorLogNo;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public UserDto(String division, String name, String doorLogNo, String dept, String team, String email,
			String status, String password, String gender) {
		super();
		this.division = division;
		this.name = name;
		this.doorLogNo = doorLogNo;
		this.dept = dept;
		this.team = team;
		this.email = email;
		this.status = status;
		this.password = password;
		this.gender = gender;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setStaffId(String cellValue) {
		// TODO Auto-generated method stub
		
	}
	public String getStaffId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}