package com.build.cloud.core.entity;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * @ClassName: DataEntity
 * @Description: 数据Entity类
 * @author: liutao
 * @date: 2018年3月17日 下午5:45:58
 * @param <ID>
 */
public abstract class DataEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	@TableField(value = "create_by", fill = FieldFill.INSERT)
	private String createBy; // 创建用户，使用登录用户名
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
	// @TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.INSERT_UPDATE)
	// private SysUserEntity updateBy;
	@TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
	private String updateBy;
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	@TableField(value = "valid", fill = FieldFill.INSERT)
	@TableLogic
	private String valid = "0"; // 删除标记 1：已删除 0：正常
	public DataEntity() {
		super();
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
