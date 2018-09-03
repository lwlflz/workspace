package com.build.cloud.modules.mat.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;


/**
 * <p>
 * 盘存表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-10
 */
@TableName("mat_take_inventory")
public class MatTakeInventoryEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    @TableField("project_id")
    @NotBlank(message = "项目id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String projectId;
    /**
     * 项目名称
     */
    @TableField("project_name")
    @NotBlank(message = "项目名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String projectName;
    /**
     * 劳务班组合同id
     */
    @TableField("con_id")
    @NotBlank(message = "劳务班组合同id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conId;
    /**
     * 劳务班组合同名称
     */
    @TableField("con_name")
    @NotBlank(message = "劳务班组合同名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conName;
    /**
     * 劳务班组id
     */
    @TableField("team_id")
    @NotBlank(message = "劳务班组id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String teamId;
    /**
     * 劳务班组名称
     */
    @TableField("team_name")
    @NotBlank(message = "劳务班组名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String teamName;
    /**
     * 盘存人
     */
    @TableField("take_by")
    @NotBlank(message = "盘存人不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String takeBy;
    /**
     * 盘存截止日期
     */
    @TableField("take_date")
    @NotNull(message = "盘存截止日期不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Date takeDate;
    /**
     * 盘存月份
     */
    @TableField("take_month")
    @NotBlank(message = "盘存月份不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String takeMonth;
    /**
     * 实际预算消耗量
     */
    @TableField("budget_amount")
    @NotNull(message = "实际预算消耗量不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal budgetAmount;
    /**
     * 工作流实例id
     */
    @TableField("activityins_id")
    private String activityinsId;
    /**
     * 状态(0-自由态;1-审核中;2-审核通过)
     */
    @TableField("return_status")
    private String returnStatus;
    /**
     * 工作流完成时间
     */
    @TableField("check_fin_time")
    private Date checkFinTime;
    /**
     * 状态(0-自由态;1-审核中;2-审核通过)
     */
    @TableField("check_status")
    private String checkStatus;
    /**
     * 出库量
     */
    @TableField(exist = false)
    private BigDecimal outNumber;
    /**
     *实际消耗量 
     */
    @TableField(exist = false)
    private BigDecimal takeNumber;
    /**
     * 盘存清单
     */
    @TableField(exist = false)
    private List<MatTakeInventorylistEntity> list;
    /**
     * activity taskId
     */
	@TableField(exist = false)
	private String taskId;
	/**
	 * 是否最后审核人(弃审判断依据)
	 */
	@TableField(exist = false)
	private boolean endCheckerFlag = false;
	/**
	 * 当前审核人列表
	 */
	@TableField(exist = false)
	private List<String> nowCheckerList;
	/**
     * activity 审核批注
     */
	@TableField(exist = false)
	private String comment;

    public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isEndCheckerFlag() {
		return endCheckerFlag;
	}

	public void setEndCheckerFlag(boolean endCheckerFlag) {
		this.endCheckerFlag = endCheckerFlag;
	}

	public List<String> getNowCheckerList() {
		return nowCheckerList;
	}

	public void setNowCheckerList(List<String> nowCheckerList) {
		this.nowCheckerList = nowCheckerList;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<MatTakeInventorylistEntity> getList() {
		return list;
	}

	public void setList(List<MatTakeInventorylistEntity> list) {
		this.list = list;
	}

	public BigDecimal getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(BigDecimal outNumber) {
		this.outNumber = outNumber;
	}

	public BigDecimal getTakeNumber() {
		return takeNumber;
	}

	public void setTakeNumber(BigDecimal takeNumber) {
		this.takeNumber = takeNumber;
	}

	public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getTakeBy() {
        return takeBy;
    }

    public void setTakeBy(String takeBy) {
        this.takeBy = takeBy;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getActivityinsId() {
        return activityinsId;
    }

    public void setActivityinsId(String activityinsId) {
        this.activityinsId = activityinsId;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Date getCheckFinTime() {
        return checkFinTime;
    }

    public void setCheckFinTime(Date checkFinTime) {
        this.checkFinTime = checkFinTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getTakeMonth() {
		return takeMonth;
	}

	public void setTakeMonth(String takeMonth) {
		this.takeMonth = takeMonth;
	}

	@Override
    public String toString() {
        return "MatTakeInventory{" +
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", conId=" + conId +
        ", conName=" + conName +
        ", takeBy=" + takeBy +
        ", takeDate=" + takeDate +
        ", budgetAmount=" + budgetAmount +
        ", activityinsId=" + activityinsId +
        ", returnStatus=" + returnStatus +
        ", checkFinTime=" + checkFinTime +
        ", checkStatus=" + checkStatus +
        "}";
    }
}
