package com.build.cloud.modules.productplan.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
@TableName("pro_plan_detail")
public class ProPlanDetail extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 生产计划id
     */
	@TableField("pro_id")
	private String proId;
	/**
     * wbs编码
     */
	@TableField("wbs_code")
	private String wbsCode;
    /**
     * wbs名称
     */
	@TableField("wbs_name")
	private String wbsName;
    /**
     * 名称属性
     */
	@TableField("name_property")
	private String nameProperty;
	/**
     * 属性类型(sub_item-分项；layered-分层；branch-分部；buildnum-楼栋号)
     */
	@TableField("property_type")
	private String propertyType;
    /**
     * 计划开始时间
     */
	@TableField("plan_begin_date")
	private Date planBeginDate;
    /**
     * 计划完成时间
     */
	@TableField("plan_end_date")
	private Date planEndDate;
    /**
     * 工期
     */
	private BigDecimal duration;
    /**
     * 建筑面积
     */
	private BigDecimal quantities;
    /**
     * 单位
     */
	private String unit;
    /**
     * 实际开始时间
     */
	@TableField("act_begin_date")
	private Date actBeginDate;
    /**
     * 实际结束时间
     */
	@TableField("act_end_date")
	private Date actEndDate;
    /**
     * 滞后/提前
     */
	@TableField("ahead_delay")
	private Integer aheadDelay;
    /**
     * 奖/罚
     */
	@TableField("award_punish")
	private String awardPunish;
	
	//行号
	@TableField("idx")
	private Integer idx;
    /**
     * 行唯一标识
     */
	@TableField("unique_id")
	private String uniqueId;
    /**
     * 父行
     */
	@TableField("parent_id")
	private String parentId;
    /**
     * 前置行
     */
	@TableField("pre_id")
	private String preId;
    /**
     * 是否已发任务单
     */
	@TableField("is_worked")
	private String isWorked;
	
	/**
     * 增行/删行
     */
	@TableField("add_delete")
	private String addDelete;
	
	/**
     * 合同拆分id
     */
	@TableField("consplit_id")
	private String consplitId;
	
	@TableField("outline_level")
	private Integer outlineLevel;
	
	private String leaf; //是否叶子节点
	
	@TableField("parent_ids")
	private String parentIds;
	
	//是否完成(0-未完成,1-已完成)
	@TableField(exist = false)
	private String finished = "0";
	
	@TableField(exist = false)
	private boolean selected = false; //是否选中
	
	@TableField(exist = false)
	private boolean enabled = true; //是否选中
	

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getWbsName() {
		return wbsName;
	}

	public void setWbsName(String wbsName) {
		this.wbsName = wbsName;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getQuantities() {
		return quantities;
	}

	public void setQuantities(BigDecimal quantities) {
		this.quantities = quantities;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getActBeginDate() {
		return actBeginDate;
	}

	public void setActBeginDate(Date actBeginDate) {
		this.actBeginDate = actBeginDate;
	}

	public Date getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}

	public Integer getAheadDelay() {
		return aheadDelay;
	}

	public void setAheadDelay(Integer aheadDelay) {
		this.aheadDelay = aheadDelay;
	}

	public String getAwardPunish() {
		return awardPunish;
	}

	public void setAwardPunish(String awardPunish) {
		this.awardPunish = awardPunish;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public String getIsWorked() {
		return isWorked;
	}

	public void setIsWorked(String isWorked) {
		this.isWorked = isWorked;
	}

	public Integer getOutlineLevel() {
		return outlineLevel;
	}

	public void setOutlineLevel(Integer outlineLevel) {
		this.outlineLevel = outlineLevel;
	}

	public String getAddDelete() {
		return addDelete;
	}

	public void setAddDelete(String addDelete) {
		this.addDelete = addDelete;
	}

	public String getConsplitId() {
		return consplitId;
	}

	public void setConsplitId(String consplitId) {
		this.consplitId = consplitId;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getWbsCode() {
		return wbsCode;
	}

	public void setWbsCode(String wbsCode) {
		this.wbsCode = wbsCode;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

}
