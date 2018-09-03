package com.build.cloud.modules.sys.entity;

import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: SysDictEntity
 * @Description: 数据字典
 * @author: liutao
 * @date: 2018年3月16日 下午5:31:18
 */
@TableName("sys_dict")
public class SysDictEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 字典名称
	 */
	@NotBlank(message = "字典名称不能为空")
	private String name;
	/**
	 * 字典类型
	 */
	@NotBlank(message = "字典类型不能为空")
	private String type;
	/**
	 * 字典码
	 */
	@NotBlank(message = "字典码不能为空")
	private String code;
	/**
	 * 字典值
	 */
	@NotBlank(message = "字典值不能为空")
	private String value;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 归属集团Code
	 */
	private String corpCode;
	/**
	 * 归属集团Name
	 */
	private String corpName;
	/**
	 * 设置：字典名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：字典名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：字典类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：字典类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：字典码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：字典码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：字典值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：字典值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
}
