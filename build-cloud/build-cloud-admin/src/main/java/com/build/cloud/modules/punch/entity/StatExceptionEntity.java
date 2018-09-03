package com.build.cloud.modules.punch.entity;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/**   
 * @ClassName: StatExceptionEntity   
 * @Description: TODO(这里用一句话描述这个类的作用)   
 * @author: liutao
 * @date: 2018年5月28日 下午2:30:30      
 */   
@TableName("stat_exception")
public class StatExceptionEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;
	/*
	 * 异常类型（1:无进场时间；2:无退场时间）
	 */
	@NotEmpty(message = "异常类型不能为空")
	private String exType;
	private Date exDate;
	@NotEmpty(message = "劳务用工编号不能为空")
	private String workerId;
	private String remark;
	@NotEmpty(message = "考勤编号不能为空")
	private Long scId;
	private Date createTime;
	/**
	 * 状态1-未处理 2-已处理
	 */
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExType() {
		return exType;
	}
	public void setExType(String exType) {
		this.exType = exType;
	}
	public Date getExDate() {
		return exDate;
	}
	public void setExDate(Date exDate) {
		this.exDate = exDate;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getScId() {
		return scId;
	}
	public void setScId(Long scId) {
		this.scId = scId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
