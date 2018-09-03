package com.build.cloud.modules.bs.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

@TableName("op_public_project")
public class OpPublicProjectEntity extends DataEntity{
	private static final long serialVersionUID = 1L;
	private String opProjectCode;
	private String opProjectName;

	public String getOpProjectCode() {
		return opProjectCode;
	}

	public void setOpProjectCode(String opProjectCode) {
		this.opProjectCode = opProjectCode;
	}

	public String getOpProjectName() {
		return opProjectName;
	}

	public void setOpProjectName(String opProjectName) {
		this.opProjectName = opProjectName;
	}
	
	
}
