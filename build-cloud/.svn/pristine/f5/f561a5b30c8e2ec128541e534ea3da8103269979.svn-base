package com.build.cloud.modules.sys.form;

public class CompanyUserForm {
	/**
	 * 登录账号ID
	 */
	private String userId;
	
	private String loginName;
	
	private String userName;
	
	private String idNum;
	
	private String phone;
	
	private String area;
	/**
	 * 用户ID
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNum() {
		if(idNum.length() > 15) {			
			StringBuilder sb = new StringBuilder(idNum);
			sb.replace(6, 14, "********");
			return sb.toString();
		}else {
			return idNum;
		}
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
}
