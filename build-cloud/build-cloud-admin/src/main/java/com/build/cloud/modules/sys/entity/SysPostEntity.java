package com.build.cloud.modules.sys.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: SysPostEntity
 * @Description: 员工岗位表
 * @author: liutao
 * @date: 2018年3月31日 上午9:28:47
 */
@TableName("sys_post")
public class SysPostEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 岗位编码
	 */
	@NotBlank(message = "岗位编码不能为空")
	private String postCode;
	/**
	 * 岗位名称
	 */
	@NotBlank(message = "岗位名称不能为空")
	private String postName;
	/**
	 * 岗位分类（高管、中层、基层）
	 */
	private String postType;
	/**
	 * 岗位排序（升序）
	 */
	private Long postSort;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 归属集团Code
	 */
	private String corpCode;
	/**
	 * 归属集团Name
	 */
	private String corpName;
	/**
	 * 状态（0正常 1停用）
	 */
	private String status;
	/**
	 * 设置：岗位编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：岗位编码
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：岗位名称
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取：岗位名称
	 */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置：岗位分类（高管、中层、基层）
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}
	/**
	 * 获取：岗位分类（高管、中层、基层）
	 */
	public String getPostType() {
		return postType;
	}
	/**
	 * 设置：岗位排序（升序）
	 */
	public void setPostSort(Long postSort) {
		this.postSort = postSort;
	}
	/**
	 * 获取：岗位排序（升序）
	 */
	public Long getPostSort() {
		return postSort;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：归属集团Code
	 */
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	/**
	 * 获取：归属集团Code
	 */
	public String getCorpCode() {
		return corpCode;
	}
	/**
	 * 设置：归属集团Name
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	/**
	 * 获取：归属集团Name
	 */
	public String getCorpName() {
		return corpName;
	}
	/**
	 * 设置：状态（0正常 1停用）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态（0正常 1停用）
	 */
	public String getStatus() {
		return status;
	}
}
