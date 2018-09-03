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
 * 入库表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-09
 */
@TableName("mat_purchase_stock_in")
public class MatPurchaseStockInEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 采购订单id
     */
    @TableField("order_id")
    @NotBlank(message = "采购订单id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String orderId;
    /**
     * 项目id
     */
    @NotBlank(message = "项目id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("project_id")
    private String projectId;
    /**
     * 项目名称
     */
    @NotBlank(message = "项目id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @TableField("project_name")
    private String projectName;
    /**
     * 供应商id
     */
    @TableField("supplier")
    @NotBlank(message = "供应商id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String supplier;
    /**
     * 供应商名称
     */
    @TableField(exist = false)
    private String supplierName;
    /**
     * 入库时间
     */
    @TableField("warehous_time")
    private Date warehousTime;
    /**
     * 入库金额(含税)
     */
    @TableField("money_tax")
    @NotNull(message = "入库金额(含税)不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal moneyTax;
    /**
     * 入库金额(无税)
     */
    @TableField("money_ntax")
    @NotNull(message = "入库金额(无税)不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal moneyNtax;
    /**
     * 运费
     */
    @TableField("freight_money")
    private BigDecimal freightMoney;
    /**
     * 单据号
     */
    @TableField("bill_code")
//    @NotBlank(message = "单据号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String billCode;
    /**
     * 工作流实例id
     */
    @TableField("activityins_id")
    private String activityinsId;
    /**
     * 弃审状态(0-未弃审，1-已弃审)
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
     * 入库详情列表
     */
    @TableField(exist= false )
    private List<MatPurchaseStockInlistEntity> list;
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
    
	
    public Date getWarehousTime() {
		return warehousTime;
	}

	public void setWarehousTime(Date warehousTime) {
		this.warehousTime = warehousTime;
	}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<MatPurchaseStockInlistEntity> getList() {
		return list;
	}

	public void setList(List<MatPurchaseStockInlistEntity> list) {
		this.list = list;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }

    public BigDecimal getMoneyNtax() {
        return moneyNtax;
    }

    public void setMoneyNtax(BigDecimal moneyNtax) {
        this.moneyNtax = moneyNtax;
    }

    public BigDecimal getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(BigDecimal freightMoney) {
        this.freightMoney = freightMoney;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
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

    @Override
    public String toString() {
        return "MatPurchaseStockIn{" +
        ", orderId=" + orderId +
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", supplier=" + supplier +
        ", supplierName=" + supplierName +
        ", moneyTax=" + moneyTax +
        ", moneyNtax=" + moneyNtax +
        ", freightMoney=" + freightMoney +
        ", billCode=" + billCode +
        ", activityinsId=" + activityinsId +
        ", returnStatus=" + returnStatus +
        ", checkFinTime=" + checkFinTime +
        ", checkStatus=" + checkStatus +
        "}";
    }
}
