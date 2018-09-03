package com.build.cloud.modules.sys.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: SysConfigEntity
 * @Description: 系统配置信息
 * @author: liutao
 * @date: 2018年3月16日 下午5:30:51
 */
@TableName("sys_config")
public class SysConfigEntity extends DataEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "参数名不能为空")
	private String key;
	@NotBlank(message = "参数值不能为空")
	private String value;
	private String remark;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
