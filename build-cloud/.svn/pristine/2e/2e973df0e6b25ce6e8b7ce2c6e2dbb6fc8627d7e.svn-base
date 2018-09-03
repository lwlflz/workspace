package com.build.cloud.modules.punch.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-08-04
 */
@TableName("dev_face_command")
public class DevFaceCommandEntity  extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
	@TableField("sz_serial")
	private String szSerial;
    /**
     * 命令
     */
	private String command;
    /**
     * 执行状态(1已执行0未执行)
     */
	@TableField("run_status")
	private String runStatus;

	public String getSzSerial() {
		return szSerial;
	}

	public void setSzSerial(String szSerial) {
		this.szSerial = szSerial;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}
}
