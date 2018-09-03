package com.build.cloud.modules.punch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.modules.mat.entity.MatPurchaseOrderlistEntity;

/**
 * 
 * @ClassName: DevDeviceEntity   
 * @Description: 考勤机
 * @author: liutao
 * @date: 2018年5月17日 上午10:39:40
 */
@TableName("dev_device")
public class DevDeviceEntity extends Model<DevDeviceEntity> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "ng_id", type = IdType.ID_WORKER_STR)
	private String ngId;
	/**
	 * 考勤机名称
	 */
	@NotBlank(message="考勤机名称不能为空", groups = {AddGroup.class,UpdateGroup.class})
	private String szName;
	/**
	 * 考勤机类型，如C330，C226
	 */
	private String szType;
	/**
	 * 设备类型 0:考勤机  1:门禁机
	 */
	private Integer stDevClass;
	/**
	 * 考勤机ip地址 
	 */
	private String szIpAddr;
	/**
	 * 子网掩码
	 */
	private String szMask;
	/**
	 * 网关
	 */
	private String szGateway;
	/**
	 * MAC地址
	 */
	private String szMac;
	/**
	 * 设备序列号 
	 */
	@NotBlank(message="设备序列号不能为空", groups = {AddGroup.class,UpdateGroup.class})
	private String szSerial;
	/**
	 * 项目ID
	 */
//	@NotBlank(message="项目id不能为空", groups = {AddGroup.class,UpdateGroup.class})
	private String projectId;
	
	private String isIn;
	
	private Date tsCreate;

	/**
	 * 设置：
	 */
	public void setNgId(String ngId) {
		this.ngId = ngId;
	}
	/**
	 * 获取：
	 */
	public String getNgId() {
		return ngId;
	}
	/**
	 * 设置：考勤机名称
	 */
	public void setSzName(String szName) {
		this.szName = szName;
	}
	/**
	 * 获取：考勤机名称
	 */
	public String getSzName() {
		return szName;
	}
	/**
	 * 设置：考勤机类型，如C330，C226
	 */
	public void setSzType(String szType) {
		this.szType = szType;
	}
	/**
	 * 获取：考勤机类型，如C330，C226
	 */
	public String getSzType() {
		return szType;
	}
	/**
	 * 设置：设备类型 0:考勤机  1:门禁机
	 */
	public void setStDevClass(Integer stDevClass) {
		this.stDevClass = stDevClass;
	}
	/**
	 * 获取：设备类型 0:考勤机  1:门禁机
	 */
	public Integer getStDevClass() {
		return stDevClass;
	}
	/**
	 * 设置：考勤机ip地址 
	 */
	public void setSzIpAddr(String szIpAddr) {
		this.szIpAddr = szIpAddr;
	}
	/**
	 * 获取：考勤机ip地址 
	 */
	public String getSzIpAddr() {
		return szIpAddr;
	}
	/**
	 * 设置：子网掩码
	 */
	public void setSzMask(String szMask) {
		this.szMask = szMask;
	}
	/**
	 * 获取：子网掩码
	 */
	public String getSzMask() {
		return szMask;
	}
	/**
	 * 设置：网关
	 */
	public void setSzGateway(String szGateway) {
		this.szGateway = szGateway;
	}
	/**
	 * 获取：网关
	 */
	public String getSzGateway() {
		return szGateway;
	}
	/**
	 * 设置：MAC地址
	 */
	public void setSzMac(String szMac) {
		this.szMac = szMac;
	}
	/**
	 * 获取：MAC地址
	 */
	public String getSzMac() {
		return szMac;
	}
	/**
	 * 设置：设备序列号 
	 */
	public void setSzSerial(String szSerial) {
		this.szSerial = szSerial;
	}
	/**
	 * 获取：设备序列号 
	 */
	public String getSzSerial() {
		return szSerial;
	}
	/**
	 * 设置：项目ID
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目ID
	 */
	public String getProjectId() {
		return projectId;
	}
	
	public String getIsIn() {
		return isIn;
	}
	public void setIsIn(String isIn) {
		this.isIn = isIn;
	}
	public Date getTsCreate() {
		return tsCreate;
	}
	public void setTsCreate(Date tsCreate) {
		this.tsCreate = tsCreate;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
}
