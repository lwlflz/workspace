package com.build.cloud.modules.ls.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.entity.DataEntity;


/**
 * <p>
 * 劳务分包合同拆分详情
 * </p>
 *
 * @author gongjy
 * @since 2018-08-27
 */
@TableName("ls_contract_plandetail")
public class LsContractPlandetailEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 劳务分包合同id
     */
    @TableField("con_id")
//    @NotBlank(message ="劳务分包合同不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String conId;
    /**
     * 生产计划详情行标识
     */
    @TableField("proplan_unique")
    @NotBlank(message ="生产计划详情行不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String proplanUnique;
    /**
     * 项目id
     */
    @TableField("project_id")
//    @NotBlank(message ="项目不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String projectId;
    /**
     * 单价
     */
    @TableField("unit_price")
//    @NotNull(message = "单价不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal unitPrice;
    /**
     * 合价
     */
    @TableField("total_price")
//    @NotNull(message = "合价不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private BigDecimal totalPrice;
    /**
     * 付款节点
     */
    @TableField("pay_node")
//    @NotBlank(message ="付款节点不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String payNode;
    /**
     * 建筑面积
     */
    @TableField("quantities")
//  @NotNull(message = "单价不能为空", groups = {AddGroup.class, UpdateGroup.class})
  private BigDecimal quantities;

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getProplanUnique() {
        return proplanUnique;
    }

    public void setProplanUnique(String proplanUnique) {
        this.proplanUnique = proplanUnique;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPayNode() {
        return payNode;
    }

    public void setPayNode(String payNode) {
        this.payNode = payNode;
    }

    public BigDecimal getQuantities() {
		return quantities;
	}

	public void setQuantities(BigDecimal quantities) {
		this.quantities = quantities;
	}

	@Override
    public String toString() {
        return "LsContractPlandetail{" +
        ", conId=" + conId +
        ", proplanUnique=" + proplanUnique +
        ", projectId=" + projectId +
        ", unitPrice=" + unitPrice +
        ", totalPrice=" + totalPrice +
        ", payNode=" + payNode +
        "}";
    }
}
