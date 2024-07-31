package com.g3.elis.dto.form;

public class UserDto 
{
	private String division;
	private String staffId;	
	private String name;
	private String doorLogNo;
	private String dept;
	private String team;
	private String email;	
	private String status;
	private String password;
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
	public UserDto(String division, String staffId, String name, String doorLogNo, String dept, String team,
			String email, String status, String password) {
		super();
		this.division = division;
		this.staffId = staffId;
		this.name = name;
		this.doorLogNo = doorLogNo;
		this.dept = dept;
		this.team = team;
		this.email = email;
		this.status = status;
		this.password = password;
	}
	
	public UserDto()
	{
		
	}
}
