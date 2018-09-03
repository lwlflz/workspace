package com.build.cloud.modules.productplan.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProPlanInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
public interface IProPlanInfoService extends IService<ProPlanInfo> {
	/**
	 * 保存生产计划和详情
	 * @param info
	 */
	void insertProPlan(ProPlanInfo info);
	
	/**
	 * 修改生产计划和详情
	 * @param info
	 */
	void updateProPlan(ProPlanInfo info);
	
	/**
	 * 查询项目对应的最大版本生产计划
	 * @param projectId
	 * @return
	 */
	Integer selectMaxVersionByProjectId(String projectId);
	
	/**
	 * 根据版本号查询生产计划
	 * @param proPlanInfo
	 * @return
	 */
	ProPlanInfo selectProPlanByVersion(ProPlanInfo proPlanInfo);
	
	/**
     * 根据项目id查询已完成合同拆分的生产计划详情
     */
	List<ProPlanDetail> selectDetail(ProPlanInfo proPlanInfo);
	
	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo
	 */
	void submit(ProPlanInfo proPlanInfo);
	
	/**
	 * 管理员审核通过操作
	 */
	void check(ProPlanInfo proPlanInfo);
	
	/**
	 * 管理员审核驳回操作
	 */
	void reject(ProPlanInfo proPlanInfo);
	
	/**
	 * 管理员审核不通过操作
	 */
	void endReturn(ProPlanInfo proPlanInfo);
}
