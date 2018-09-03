package com.build.cloud.modules.punch.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;

/**
 * <p>Title: DevInfo</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年8月4日 下午3:51:57
 */
@TableName("dev_info")
public class DevInfoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 设备序列号
	 */
	@TableField("sz_serial")
	@NotBlank(message = "设备序列号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String szSerial;
	
	/**
	 * 公司ID
	 */
	@TableField("company_id")
	private String companyId;
	@TableField(exist = false)
	List<Map<String,Object>> list;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSzSerial() {
		return szSerial;
	}
	public void setSzSerial(String szSerial) {
		this.szSerial = szSerial;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

}
