package com.build.cloud.modules.job.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;
/**
 * @ClassName: ScheduleJobLogEntity
 * @Description: 定时任务日志
 * @author: liutao
 * @date: 2018年3月16日 下午3:00:14
 */
@TableName("schedule_job_log")
public class ScheduleJobLogEntity extends DataEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 任务id
	 */
	private String jobId;
	/**
	 * spring bean名称
	 */
	private String beanName;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * 任务状态 0：成功 1：失败
	 */
	private Integer status;
	/**
	 * 失败信息
	 */
	private String error;
	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
}
