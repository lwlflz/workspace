package com.build.cloud.modules.sys.entity.vo;

import java.math.BigDecimal;

/**
 * 
* Title: CompanyTreeVo
* Description: 公司树结构
* @author 鲁四围 
* @date 2018年4月4日
 */
public class CompanyTreeVo {
	/**
	 * 公司编码
	 */
	private String companyCode;
	/**
	 * 父级编号
	 */
	private String parentCode;
	
	/**
	 * 本级排序号（升序）
	 */
	private BigDecimal treeSort;
	/**
	 * 公司名称
	 */
	private String companyName;
	
		
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public BigDecimal getTreeSort() {
		return treeSort;
	}

	public void setTreeSort(BigDecimal treeSort) {
		this.treeSort = treeSort;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
}
