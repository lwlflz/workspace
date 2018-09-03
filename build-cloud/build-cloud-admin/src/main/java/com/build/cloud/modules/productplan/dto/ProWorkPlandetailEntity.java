package com.build.cloud.modules.productplan.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.core.entity.BaseEntity;

/**
 * 
* Title: ProWorkPlandetailEntity
* Description: 任务单生产计划关系 表
* @author 鲁四围 
* @date 2018年4月24日
 */
@TableName("pro_work_plandetail")
public class ProWorkPlandetailEntity extends BaseEntity {
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	
	@TableField(value="workorder_id")
	private String workorderId;
	
	@TableField(value="proplan_unique")
	private String proplanUnique;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public String getProplanUnique() {
		return proplanUnique;
	}

	public void setProplanUnique(String proplanUnique) {
		this.proplanUnique = proplanUnique;
	}

}
