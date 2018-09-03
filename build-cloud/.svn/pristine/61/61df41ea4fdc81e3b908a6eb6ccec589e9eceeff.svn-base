/**
 * 
 */
package com.build.cloud.modules.bs.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;

/**
 * @className BsTeamEntity
 * @descripion 班组档案
 * @author huangchao
 * @date 2018年4月12日下午7:25:28
 */
@TableName("bs_team")
public class BsTeamEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 班组编码
	 */
	@NotBlank(message = "班组编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String teamCode;            
	/**
	 * 班组名称
	 */
	@NotBlank(message = "班组名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String teamName;           
	/**
	 * 班组分类(单选。
	 *  1、钢筋班组
	 *  2、泥工班组 
	 *  3、架子工班组 
	 *  4、木工班组 
	 *  5、内墙装饰班组 
	 *  6、泥工砌体班组 
	 *  7、外墙装饰班组)
	 */
	private String teamType;   
	private String teamTypeName;
	
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 班组负责人
	 */
	private String teamLeader; 
	/**
	 * 班组负责人ID
	 */
	private String teamLeaderId; 
	/**
	 * 班组负责人身份证号
	 */
	private String teamIdnum;           
	/**
	 * 负责人电话
	 */
	private String phone;               
	/**
	 * 负责人家庭住址
	 */
	private String teamAddress;        
	/**
	 * 所属地区
	 */
	private String belong;              
	/**
	 * 状态（0正常 1停用）
	 */
	private String status;               
	/**
	 * 删除标记（0正常 1删除）
	 */
	private String valid;
	/**
	 * 劳务班组合同id
	 */
	@TableField(exist = false)
	private String conId;
	/**
	 * 劳务班组合同名称
	 */
	@TableField(exist = false)
	private String conName;
	
	
	public String getConId() {
		return conId;
	}
	public void setConId(String conId) {
		this.conId = conId;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getTeamIdnum() {
		return teamIdnum;
	}
	public void setTeamIdnum(String teamIdnum) {
		this.teamIdnum = teamIdnum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTeamAddress() {
		return teamAddress;
	}
	public void setTeamAddress(String teamAddress) {
		this.teamAddress = teamAddress;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	/**
	 * @return the teamTypeName
	 */
	public String getTeamTypeName() {
		return teamTypeName;
	}
	/**
	 * @param teamTypeName the teamTypeName to set
	 */
	public void setTeamTypeName(String teamTypeName) {
		this.teamTypeName = teamTypeName;
	}
	/**
	 * @return the teamLeaderId
	 */
	public String getTeamLeaderId() {
		return teamLeaderId;
	}
	/**
	 * @param teamLeaderId the teamLeaderId to set
	 */
	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
}
