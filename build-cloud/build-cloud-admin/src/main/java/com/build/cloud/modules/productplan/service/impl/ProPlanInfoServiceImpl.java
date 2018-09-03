package com.build.cloud.modules.productplan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.ls.entity.LsContractPlandetailEntity;
import com.build.cloud.modules.ls.service.ILsContractPlandetailService;
import com.build.cloud.modules.productplan.dao.ProPlanInfoMapper;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProPlanInfo;
import com.build.cloud.modules.productplan.service.IProContractPlandetailService;
import com.build.cloud.modules.productplan.service.IProContractService;
import com.build.cloud.modules.productplan.service.IProPlanDetailService;
import com.build.cloud.modules.productplan.service.IProPlanInfoService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangsen
 * @since 2018-04-23
 */
@Service
public class ProPlanInfoServiceImpl extends ServiceImpl<ProPlanInfoMapper, ProPlanInfo> implements IProPlanInfoService {
	@Autowired
	private IProPlanDetailService proPlanDetailService;
	
	@Autowired
	private IProContractPlandetailService proContractPlandetailService;
	
	@Autowired
	private IBsProjectService bsProjectService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private IProContractService proContractService;
	
	@Autowired
	private ILsContractPlandetailService lsContractPlandetailService;
	
	/**
	 * 保存生产计划和详情
	 * @param info
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertProPlan(ProPlanInfo info) {
		//保存主表
		Integer currVersion = 1;
		Integer maxVersion = selectMaxVersionByProjectId(info.getProjectId());
		if(maxVersion!=null&&!Objects.equal(maxVersion, 0)){
			currVersion = maxVersion + 1;
		}
//		info.setEffStatus("0");//生效标志-初始未生效 数据库默认
//		info.setCheckStatus("0");//审核状态-初始自由态  数据库默认
		info.setVersion(currVersion);
		baseMapper.insert(info);
		
		//保存详情表
		List<ProPlanDetail> proPlanDetailList = info.getDetailList();
		if(proPlanDetailList!=null&&proPlanDetailList.size()>0){
			for (ProPlanDetail proPlanDetail : proPlanDetailList) {
				//用作增行同步到合同拆分
				if(Objects.equal("del", proPlanDetail.getAddDelete())){
					proPlanDetail.setValid("1");
				}
				proPlanDetail.setProId(info.getId());
				proPlanDetail.setId(null);
			}
			proPlanDetailService.insertBatch(proPlanDetailList);
		}
	}
	
	/**
	 * 修改生产计划和详情
	 * @param info
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProPlan(ProPlanInfo info) {
		//物理删除原纪录
		baseMapper.physicsDelete(info.getId());
		proPlanDetailService.physicsDelete(info.getId());
		//保存主表
//		info.setEffStatus("0");//生效标志-初始未生效 数据库默认
//		info.setCheckStatus("0");//审核状态-初始自由态  数据库默认
		baseMapper.insert(info);
		
		//保存详情表
		List<ProPlanDetail> proPlanDetailList = info.getDetailList();
		for (ProPlanDetail proPlanDetail : proPlanDetailList) {
			if(Objects.equal("del", proPlanDetail.getAddDelete())){
				proPlanDetail.setValid("1");
			}
			proPlanDetail.setProId(info.getId());
		}
		proPlanDetailService.insertBatch(proPlanDetailList);
	}

	/**
	 * 查询项目对应的最大版本生产计划
	 * @param projectId
	 * @return
	 */
	@Override
	public Integer selectMaxVersionByProjectId(String projectId) {
		return baseMapper.selectMaxVersionByProjectId(projectId);
	}

	/**
	 * 根据版本号查询生产计划
	 * @param proPlanInfo
	 * @return
	 */
	@Override
	public ProPlanInfo selectProPlanByVersion(ProPlanInfo proPlanInfo) {
		ProPlanInfo res = null;
		Map<String,String> proMap = Maps.newHashMap();
		//如果没传主键id取最新版本,否则根据id查询
		EntityWrapper<ProPlanInfo> ewInfo = new EntityWrapper<ProPlanInfo>();
		if(StringUtils.isEmpty(proPlanInfo.getId())){
			//获取总包项目id
			proMap = bsProjectService.quryProProjectId(proPlanInfo.getProjectId());
			String proId = MapUtil.getStr(proMap, "projectId");
			proPlanInfo.setProjectId(proId);
			
			ewInfo.setEntity(proPlanInfo);
			ewInfo.orderBy("version", false);//降序排序
		}else{
			ewInfo.eq("id", proPlanInfo.getId());
		}
		
		List<ProPlanInfo> resList = baseMapper.selectList(ewInfo);
		if(resList!=null&&resList.size()>0){
			res = resList.get(0);
			BsProjectEntity bsProject = bsProjectService.selectById(res.getProjectId());
			if(bsProject!=null){
				res.setProjectName(bsProject.getProjectName());
			}
			
			List<ProPlanDetail> detailList = Lists.newArrayList();
			
			String roleType = proMap.get(Constant.COMPANY_ROLE_TYPE);
			res.setCompanyRoleType(roleType);
			if(roleType!=null&&Objects.equal(roleType, "3")){//分包 查询当前劳务公司对应合同拆分关联的生产计划
				Map<String,String> paramMap = Maps.newHashMap();
				paramMap.put("proId", res.getId());
				paramMap.put("relationCompanyId", ShiroUtils.getUserEntity().getCompanyId());
				detailList = proPlanDetailService.selectAuthPlanDetailList(paramMap);
				if(!CollectionUtils.isEmpty(detailList)){
					EntityWrapper<ProPlanDetail> paramEw = new EntityWrapper<ProPlanDetail>();
					paramEw.in("property_type", "build,branch");
					paramEw.eq("pro_id", res.getId());
					paramEw.orderBy("idx", true);
					List<ProPlanDetail> tempList = proPlanDetailService.selectList(paramEw);
					if(!CollectionUtils.isEmpty(tempList)){
						tempList.addAll(detailList);
						detailList = tempList;
					}
				}
			}else{
				ProPlanDetail detail = new ProPlanDetail();
				detail.setProId(res.getId());
				EntityWrapper<ProPlanDetail> ew = new EntityWrapper<ProPlanDetail>();
				ew.setEntity(detail);
				ew.orderBy("idx", true);
				detailList = proPlanDetailService.selectList(ew);
			}
			
			res.setDetailList(detailList);
			
			//查询此生产计划详情中所有已关联的合同拆分的项目信息
			List<String> projectIdList = Lists.newArrayList();
			for (ProPlanDetail proPlanDetail : detailList) {
				if(!StringUtils.isEmpty(proPlanDetail.getConsplitId())){
					projectIdList.add(proPlanDetail.getConsplitId());
				}
			}
			List<String> newList = new ArrayList<String>(new HashSet<String>(projectIdList));
			if(newList!=null&&newList.size()>0){
				List<BsProjectEntity> projectList = bsProjectService.selectBatchIds(newList);
				res.setProjectList(projectList);
			}
			
		}
		
		if(res!=null){
			//版本号列表
			List<Integer> versionList = Lists.newArrayList();
			EntityWrapper<ProPlanInfo> ew = new EntityWrapper<ProPlanInfo>();
			ew.eq("project_id", proPlanInfo.getProjectId());
			ew.orderBy("version", false);
			List<ProPlanInfo> verPanList = baseMapper.selectList(ew);
			for (ProPlanInfo ppi : verPanList) {
				versionList.add(ppi.getVersion());
			}
			res.setVersionList(versionList);
		}
		
		if(res!=null&&!StringUtils.isEmpty(res.getActivityinsId())
				&&!Objects.equal(res.getReturnStatus(), "1")){
			//设置是否允许弃审
			Integer maxVersion = selectMaxVersionByProjectId(res.getProjectId());
			String endChecker = activityService.getEndChecker(res.getActivityinsId());
			if(Objects.equal(ShiroUtils.getUserId(), endChecker)
					&&Objects.equal(res.getCheckStatus(), "2")
					&&Objects.equal(res.getVersion(), maxVersion)){
				res.setEndCheckerFlag(true);
			}
			//设置当前审核人id
			List<String> checkerList = activityService.findNowChecker(res.getActivityinsId());
			if(checkerList!=null&&checkerList.size()>0){
				res.setNowCheckerList(checkerList);
			}
			//会签时,工作流当前任务列表,当前用户等于任务办理人时,取此任务的任务id为执行任务id
			List<Task> taskList = activityService.getNowTaskList(res.getActivityinsId());
			for (Task task : taskList) {
				if(Objects.equal(task.getAssignee(), ShiroUtils.getUserId())){
					res.setTaskId(task.getId());
				}
			}
		}
		
//		Integer version = selectMaxVersionByProjectId(proPlanInfo.getProjectId());
//		
//		if(Objects.equal(version, res.getVersion())&&Objects.equal("1", res.getEffStatus())){
//			
//		}
		
		return res;
	}
	
	/**
     * 根据项目id查询已完成合同拆分的生产计划详情
     */
	@Override
	public List<ProPlanDetail> selectDetail(ProPlanInfo proPlanInfo) {
		//获取总包项目id
		Map<String,String> proMap = bsProjectService.quryProProjectId(proPlanInfo.getProjectId());
		String proId = MapUtil.getStr(proMap, "projectId");
		
		//查已生效的生产计划主表
		EntityWrapper<ProPlanInfo> ppiEw = new EntityWrapper<ProPlanInfo>();
		ppiEw.eq("project_id", proId);
		ppiEw.eq("eff_status", "1");
		ProPlanInfo ppi = this.selectOne(ppiEw);
		if(ppi==null){
			return null;
		}
		//生产计划查详情
		EntityWrapper<ProPlanDetail> ppdEw = new EntityWrapper<ProPlanDetail>();
		ppdEw.eq("pro_id", ppi.getId());
		ppdEw.orderBy("idx",true);
		List<ProPlanDetail> detailList = Lists.newArrayList();
		
		String roleType = proMap.get(Constant.COMPANY_ROLE_TYPE);
		if(roleType!=null&&Objects.equal(roleType, "3")){
			String prId = proMap.get(Constant.PRO_ID);
			String beneralcontractorId = proMap.get(Constant.CompanyProRole.BENERALCONTRACTOR.getName());
			String relationCompanyId = ShiroUtils.getUserEntity().getCompanyId();
			Map<String,String> laborParam = Maps.newHashMap();
			laborParam.put("proId", ppi.getId());
			laborParam.put("prId", prId);
			laborParam.put("beneralcontractorId", beneralcontractorId);
			laborParam.put("relationCompanyId", relationCompanyId);
			detailList = proPlanDetailService.selectLaborPlanDetailList(laborParam);
			if(!CollectionUtils.isEmpty(detailList)){
				EntityWrapper<ProPlanDetail> paramEw = new EntityWrapper<ProPlanDetail>();
				paramEw.in("property_type", "build,branch");
				paramEw.eq("pro_id", ppi.getId());
				paramEw.orderBy("idx", true);
				List<ProPlanDetail> tempList = proPlanDetailService.selectList(paramEw);
				if(!CollectionUtils.isEmpty(tempList)){
					tempList.addAll(detailList);
					detailList = tempList;
				}
			}
			
			//查劳务班组合同
			for (ProPlanDetail proPlanDetail : detailList) {
				if(StrUtil.isNotBlank(proPlanDetail.getConsplitId())&&StrUtil.isNotBlank(proPlanDetail.getUniqueId())
						&&Objects.equal(proPlanDetail.getPropertyType(),"sub_item")){
					EntityWrapper<ProContractPlandetail> ew = new EntityWrapper<ProContractPlandetail>();
					ew.eq("con_id", proPlanDetail.getConsplitId());
					ew.eq("proplan_unique", proPlanDetail.getUniqueId());
					ew.eq("valid", "0");
					ProContractPlandetail pcp = proContractPlandetailService.selectOne(ew);
					if(pcp!=null){
						proPlanDetail.setSelected(true);
						proPlanDetail.setFinished(pcp.getFinished());
					}
				}
			}
		}else{
			detailList = proPlanDetailService.selectList(ppdEw);
			
			//查劳务分包合同
			for (ProPlanDetail proPlanDetail : detailList) {
				if(StrUtil.isNotBlank(proPlanDetail.getConsplitId())&&StrUtil.isNotBlank(proPlanDetail.getUniqueId())
						&&Objects.equal(proPlanDetail.getPropertyType(),"layered")){
					EntityWrapper<LsContractPlandetailEntity> ew = new EntityWrapper<LsContractPlandetailEntity>();
					ew.eq("con_id", proPlanDetail.getConsplitId());
					ew.eq("proplan_unique", proPlanDetail.getUniqueId());
					ew.eq("valid", "0");
					LsContractPlandetailEntity lcp = lsContractPlandetailService.selectOne(ew);
					if(lcp!=null){
						proPlanDetail.setSelected(true);
					}
					
					//查询劳务合同对应的任务单子集是否全部完成,全部完成则此劳务合同对应的任务单也为已完成
					Map<String,String> paramMap = Maps.newHashMap();
					paramMap.put("uniqueId", proPlanDetail.getUniqueId());
					paramMap.put("proId", ppi.getId());
					Map<String,Integer> resMap = proContractPlandetailService.selectLaborFinishedCount(paramMap);
					if(MapUtil.getInt(resMap, "tcount")<=MapUtil.getInt(resMap, "finishedCount")){
						proPlanDetail.setFinished("1");
					}
				}
			}
		}
		
		return detailList;
		
		//查询出子节点,递归找出父节点
//		Map<String,ProPlanDetail> obMap = Maps.newHashMap();
//		Map<String,List<String>> map = Maps.newHashMap();
//		for (ProPlanDetail proPlanDetail : detailList) {
//			String parentId = proPlanDetail.getParentId();
//			String uid = proPlanDetail.getUniqueId();
//			obMap.put(uid, proPlanDetail);
//			if(map.containsKey(parentId)){
//				List<String> childList = map.get(parentId);
//				childList.add(uid);
//				map.put(parentId, childList);
//			}else{
//				List<String> childList = Lists.newArrayList();
//				childList.add(uid);
//				map.put(parentId, childList);
//			}
//		}
//		
//		//递归，过滤
//		Set<String> resSet = Sets.newHashSet();
// 		for (ProPlanDetail proPlanDetail : detailList) {
//			String uid = proPlanDetail.getUniqueId();
//			String leaf = proPlanDetail.getLeaf();
//			
//			if(!Objects.equal(leaf, "1")){
//				continue;
//			}
//			resSet.add(uid);
//			getAllIds(uid,detailList,map,resSet);
//		}
//		//组合成结果list
//		List<ProPlanDetail> ppdResList = Lists.newArrayList();
//		for (String uid : resSet) {
//			ppdResList.add(obMap.get(uid));
//		}
		
//		return ppdResList;
	}
	
//	private void getAllIds(String uid,List<ProPlanDetail> ppdList,
//			Map<String,List<String>> map,Set<String> resSet){
//		for (ProPlanDetail ppd : ppdList) {
//			String parentId = ppd.getParentId();
//			if(map.get(parentId).contains(uid)){
//				resSet.add(parentId);
//				if(!StringUtils.isEmpty(parentId)&&!Objects.equal(parentId, "0")){
//					getAllIds(parentId,ppdList,map,resSet);
//				}
//			}
//		}
//	}

	/**
	 * 提交审核，远程调用创建activity工作流实例
	 * @param proPlanInfo(id)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void submit(ProPlanInfo proPlanInfo) {
		ProPlanInfo info = baseMapper.selectById(proPlanInfo.getId());
		
		//如果流程存在且未弃审
		if(!StringUtils.isEmpty(info.getActivityinsId())
				&&Objects.equal(info.getCheckStatus(), "0")
				&&Objects.equal(info.getReturnStatus(), "0")){
			//完成节点
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setUserId(ShiroUtils.getUserId());
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			activityService.completeTaskByInsId(info.getActivityinsId(),tmp);
			
			if(!StringUtils.isEmpty(info.getActivityinsId())){
				ProPlanInfo upInfo = new ProPlanInfo();
				upInfo.setId(proPlanInfo.getId());
				upInfo.setActivityinsId(info.getActivityinsId());
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}else if((StringUtils.isEmpty(info.getActivityinsId())||Objects.equal(info.getReturnStatus(), "1"))
				&&Objects.equal(info.getCheckStatus(), "0")){//如果流程不存在或流程已弃审
			TaskIdMapPojo tmp = new TaskIdMapPojo();
			tmp.setInsKey(ActivityConstant.ProcessKey.ACT_DEF_PRO_PLAN.value);//流程定义key
			tmp.setUserId(ShiroUtils.getUserId());
			
			Map<String,Object> localMap = Maps.newHashMap();
			localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_SUBMIT.value);
			tmp.setLocalMap(localMap);
			
			Map<String,Object> map = Maps.newHashMap();
			map.put(ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value, ShiroUtils.getUserId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value, proPlanInfo.getId());
			map.put(ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value, 
					ActivityConstant.BillType.ACT_BILL_PRO_PLAN.value);
			map.put(ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value, proPlanInfo.getProjectName());
			tmp.setMap(map);
			String activityInsId = activityService.startAndComplete(tmp);
			
			if(!StringUtils.isEmpty(activityInsId)){
				ProPlanInfo upInfo = new ProPlanInfo();
				upInfo.setId(proPlanInfo.getId());
				upInfo.setActivityinsId(activityInsId);
				upInfo.setCheckStatus("1");//审核中
				upInfo.setReturnStatus("0");//未弃审
				baseMapper.updateById(upInfo);
			}
		}
	}
	
	/**
	 * 管理员审核通过操作
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void check(ProPlanInfo proPlanInfo) {
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(proPlanInfo.getTaskId());
		taskPojo.setComment(proPlanInfo.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());
		
		Map<String,Object> localMap = Maps.newHashMap();
		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_PASS.value);
		taskPojo.setLocalMap(localMap);
		activityService.completeTaskByTaskIdAndMap(taskPojo);
		
		ProcessInstance pi = activityService.queryProcessInstance(proPlanInfo.getActivityinsId());
		//流程结束
		if (pi == null) {  
			//修改单据状态为审核通过,当前版本生产计划生效标记改为已生效
			ProPlanInfo upInfo = new ProPlanInfo();
			upInfo.setId(proPlanInfo.getId());
			upInfo.setCheckStatus("2");//审核通过
			upInfo.setEffStatus("1");//已生效
			upInfo.setCheckFinTime(new Date());
			baseMapper.updateById(upInfo);
			//其他版本改为失效
			EntityWrapper<ProPlanInfo> ew = new EntityWrapper<ProPlanInfo>();
			ew.eq("project_id", proPlanInfo.getProjectId());
			ew.ne("id", proPlanInfo.getId());
			ProPlanInfo upInfo2 = new ProPlanInfo();
			upInfo2.setEffStatus("0");
			baseMapper.update(upInfo2, ew);
			
			//增行和删行审核通过后反馈到合同拆分
//			synConSplit(proPlanInfo);
        }
	}
	
	/**
	 * 管理员审核驳回操作
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void reject(ProPlanInfo proPlanInfo) {
		//工作流驳回
		TaskIdMapPojo taskPojo = new TaskIdMapPojo();
		taskPojo.setTaskId(proPlanInfo.getTaskId());
		taskPojo.setComment(proPlanInfo.getComment());
		taskPojo.setUserId(ShiroUtils.getUserId());
		
//		Map<String,Object> localMap = Maps.newHashMap();
//		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
//		taskPojo.setLocalMap(localMap);
		
		boolean isReturnSubmit = activityService.backProcess(taskPojo);
		if(isReturnSubmit){
			//修改单据状态为自由态
			ProPlanInfo upInfo = new ProPlanInfo();
			upInfo.setId(proPlanInfo.getId());
			upInfo.setCheckStatus("0");//自由态
			baseMapper.updateById(upInfo);
		}
	}
	
	/**
	 * 弃审操作
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void endReturn(ProPlanInfo proPlanInfo) {
		ProcessInstance ins = activityService.findProcessInstance(proPlanInfo.getActivityinsId());
		if(ins==null){//表示流程结束
			//修改单据状态为自由态，已弃审
			ProPlanInfo upInfo = new ProPlanInfo();
			upInfo.setId(proPlanInfo.getId());
			upInfo.setCheckStatus("0");//自由态
			upInfo.setReturnStatus("1");//已弃审
			upInfo.setEffStatus("0");//当前版本失效
			baseMapper.updateById(upInfo);
			
			//修改前一个版本的生产计划为生效状态
			ProPlanInfo resInfo = baseMapper.selectById(proPlanInfo.getId());
			Integer version = resInfo.getVersion();
			version = version - 1;
			if(version>0){
				ProPlanInfo upHisInfo = new ProPlanInfo();
				upHisInfo.setEffStatus("1");
				EntityWrapper<ProPlanInfo> upHisEw = new EntityWrapper<>();
				upHisEw.eq("project_id", resInfo.getProjectId());
				upHisEw.eq("version", version);
				baseMapper.update(upHisInfo, upHisEw);
			}
		}
	}
	
	/**
	 * 增行和删行审核通过后反馈到合同拆分
	 * @param proPlanInfo
	 */
	private void synConSplit(ProPlanInfo proPlanInfo){
//		ProPlanInfo info = baseMapper.selectById(proPlanInfo.getId());
//		info.getProjectId();
		Set<String> conIdSet = Sets.newHashSet();
		
		//增行删行处理反馈到合同拆分关联
//		Integer maxLevel = 1;
		EntityWrapper<ProPlanDetail> addDelEw = new EntityWrapper<ProPlanDetail>();
		addDelEw.in("add_delete", "add,del");
		addDelEw.eq("pro_id", proPlanInfo.getId());
		addDelEw.orNew("valid='1'")
			.in("add_delete", "add,del")
			.eq("pro_id", proPlanInfo.getId());//这里需要查删行
		List<ProPlanDetail> detailList = proPlanDetailService.selectList(addDelEw);
		List<ProContractPlandetail> addList = Lists.newArrayList();
		for (ProPlanDetail proPlanDetail : detailList) {
//			if(proPlanDetail.getOutlineLevel()!=null&&proPlanDetail.getOutlineLevel()>maxLevel){
//				maxLevel = proPlanDetail.getOutlineLevel();
//			}
			
			if(Objects.equal(proPlanDetail.getAddDelete(), "add")){
				ProContractPlandetail proContractPlandetail = new ProContractPlandetail();
				proContractPlandetail.setConId(proPlanDetail.getConsplitId());
				proContractPlandetail.setProplanUnique(proPlanDetail.getUniqueId());
				proContractPlandetail.setProjectId(proPlanInfo.getProjectId());
				addList.add(proContractPlandetail);
			}else if(Objects.equal(proPlanDetail.getAddDelete(), "del")
					&&Objects.equal(proPlanDetail.getValid(), "1")
					&&Objects.equal(proPlanDetail.getIsWorked(), "0")){
				EntityWrapper<ProContractPlandetail> delEw= new EntityWrapper<ProContractPlandetail>();
				delEw.eq("con_id", proPlanDetail.getConsplitId());
				delEw.eq("proplan_unique", proPlanDetail.getUniqueId());
				delEw.eq("project_id", proPlanInfo.getProjectId());
				proContractPlandetailService.delete(delEw);
			}
			//合同id集合,用来重新计算每个合同拆分的父节点的合价等数值
			conIdSet.add(proPlanDetail.getConsplitId());
		}
		if(addList!=null&&addList.size()>0){
			proContractPlandetailService.insertBatch(addList);
		}
		
//		computeParentNode(conIdSet,proPlanInfo.getId(),maxLevel);
	}
	
//	private void computeParentNode(Set<String> conIdList,String planInfoId,Integer maxLevel){
//		for (String string : conIdList) {
//			EntityWrapper<ProContractPlandetail> ew = new EntityWrapper<ProContractPlandetail>();
//			ew.eq("con_id", string);
//			List<ProContractPlandetail> detailList = proContractPlandetailService.selectList(ew);
//			for (ProContractPlandetail proContractPlandetail : detailList) {
//				proContractPlandetail.setId(null);
//				EntityWrapper<ProPlanDetail> ppdEw = new EntityWrapper<ProPlanDetail>();
//				ppdEw.eq("pro_id", planInfoId);
//				ppdEw.eq("unique_id", proContractPlandetail.getProplanUnique());
//				ppdEw.orNew("valid='1'")
//					.eq("pro_id", planInfoId)
//					.eq("unique_id", proContractPlandetail.getProplanUnique());
//				ProPlanDetail ppd = proPlanDetailService.selectOne(ppdEw);
//				proContractPlandetail.setPlanDetail(ppd);
//			}
//			compute(detailList,maxLevel);
//			//删除原表数据，新增更新父节点后的数据
//			proContractPlandetailService.delete(ew);
//			proContractPlandetailService.insertBatch(detailList);
//		}
//	}
	
//	private void compute(List<ProContractPlandetail> list,Integer outlineLevel){
//		if(Objects.equal(outlineLevel, 1)){
//			return;
//		}
//		
//		boolean flag = false;
//		Map<String,List<ProContractPlandetail>> parentIdMap = Maps.newHashMap();
//		for (ProContractPlandetail pcpDetail : list) {	
//			//转换成 [父节点：父节点下所有子节点集合]
//			String parentId = pcpDetail.getPlanDetail().getParentId();
//			if(StringUtils.isEmpty(parentId)){
//				continue;
//			}
//			
//			Integer outLevel = pcpDetail.getPlanDetail().getOutlineLevel();
//			if(Objects.equal(outLevel, outlineLevel)){
//				flag = true;
//			}
//			
//			String key = parentId+","+outLevel.toString();
//			List<ProContractPlandetail> tempList = Lists.newArrayList();
//			if(parentIdMap.containsKey(key)){
//				tempList = parentIdMap.get(key);
//			}
//			tempList.add(pcpDetail);
//			parentIdMap.put(key, tempList);
//		}
//		//更新list中outlineLevel对应的父节点的计算数据
//		if(flag){
//			for (String key : parentIdMap.keySet()) {
//				String level = key.split(",")[1];
//				String uniqueId = key.split(",")[0];
//				if(Objects.equal(new Integer(level), outlineLevel)){
//					List<ProContractPlandetail> computeList = parentIdMap.get(key);
//					BigDecimal totalPrice = new BigDecimal(0);
//					for (ProContractPlandetail compute : computeList) {
//						totalPrice = totalPrice.add(compute.getTotalPrice());
//					}
//					for (ProContractPlandetail proContractPlandetail : list) {
//						if(Objects.equal(proContractPlandetail.getPlanDetail().getUniqueId(),uniqueId)){
//							proContractPlandetail.setTotalPrice(totalPrice);
//						}
//					}
//				}
//			}
//		}
//		
//		outlineLevel--;
//		compute(list,outlineLevel);
//	}

}
