package com.build.cloud.modules.bs.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: BsWCUEntity
 * @Description: 劳务用工与公司与用户关联表，记录劳务用工的历史工作公司
 * @author: liutao
 * @date: 2018年4月13日 下午3:23:50
 */
@TableName("bs_w_c_u")
public class BsWCUEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 公司ID，冗余字段，查询不使用，只在需要查看历史公司的时候使用
	 */
	private String companyId;
	/**
	 * 
	 */
	private String workerId;
	/**
	 * 状态(0:正常 1:封存)
	 */
	private String status;
	/**
	 * 设置：
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：公司ID，冗余字段，查询不使用，只在需要查看历史公司的时候使用
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：公司ID，冗余字段，查询不使用，只在需要查看历史公司的时候使用
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：
	 */
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	/**
	 * 获取：
	 */
	public String getWorkerId() {
		return workerId;
	}
	/**
	 * 设置：状态(0:正常 1:封存)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0:正常 1:封存)
	 */
	public String getStatus() {
		return status;
	}
}
