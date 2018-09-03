package com.build.cloud.modules.productplan.dto;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-06-13
 */
@TableName("act_hi_comment")
public class ActHiComment extends Model<ActHiComment> {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
	private String id;
	@TableField("TYPE_")
	private String type;
	@TableField("TIME_")
	private Date time;
	@TableField("USER_ID_")
	private String userId;
	@TableField("TASK_ID_")
	private String taskId;
	@TableField("PROC_INST_ID_")
	private String procInstId;
	@TableField("ACTION_")
	private String action;
	@TableField("MESSAGE_")
	private String message;
	@TableField("FULL_MSG_")
	private byte[] fullMsg;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getFullMsg() {
		return fullMsg;
	}

	public void setFullMsg(byte[] fullMsg) {
		this.fullMsg = fullMsg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
