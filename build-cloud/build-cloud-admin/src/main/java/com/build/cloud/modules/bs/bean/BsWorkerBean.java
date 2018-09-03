package com.build.cloud.modules.bs.bean;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BsWorkerEntity
 * @Description: 劳务用工
 * @author: liutao
 * @date: 2018年4月13日 下午3:22:56
 */
public class BsWorkerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证
	 */
	private String idcard;
	/**
	 * 工种(1、钢筋工 2、混凝土工 3、架子工 4、木工 5、装饰工 6、砌体工 7、防水工 8、水电工 9、抹灰工)
	 */
	private String workType;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 银行账号
	 */
	private String bankAccount;
	/**
	 * 开户行
	 */
	private String bank;
	/**
	 * 工价
	 */
	private Double wage;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 技能等级(1、大工 2、小工)
	 */
	private String skillLevel;
	/**
	 * 所属地区
	 */
	private String area;
	/**
	 * 是否实名认证(默认0,0：否，1：是)
	 */
	private String isRealName;
	private String companyName;
	private Date entryDate;
	private String attendTime;
	private String loginName;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Double getWage() {
		return wage;
	}
	public void setWage(Double wage) {
		this.wage = wage;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
