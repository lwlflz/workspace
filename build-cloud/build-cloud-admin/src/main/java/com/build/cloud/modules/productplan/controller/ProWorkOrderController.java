 package com.build.cloud.modules.productplan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProWorkOrderEntity;
import com.build.cloud.modules.productplan.form.ReturnFrom;
import com.build.cloud.modules.productplan.form.TaskListForm;
import com.build.cloud.modules.productplan.service.IProContractPlandetailService;
import com.build.cloud.modules.productplan.service.IProPlanDetailService;
import com.build.cloud.modules.productplan.service.IProWorkOrderService;

import cn.hutool.core.util.StrUtil;



/**
 * 
* Title: ProWorkOrderController
* Description: 任务单
* @author 鲁四围 
* @date 2018年4月25日
 */
@RestController
@RequestMapping("pp/proWork")
public class ProWorkOrderController extends AbstractController {
	
	@Autowired
	private IProWorkOrderService proWorkOrderService;
	@Autowired
	private IProPlanDetailService proPlanDetailService;	//生产计划	
	@Autowired
	private IProContractPlandetailService proContractPlandetailService;

	/**
	 * 列表
	 */
	@RequestMapping("/v1/list")
	//@RequiresPermissions("pp:proWork:list")
	public Result list(@RequestParam Map<String, Object> params) {
		params.put("companyId",getCurrentCompanyId());//查询当前公司,数据权限控制
		PageUtils page = proWorkOrderService.queryPage(params);
		return Result.ok().put(page);
	}
	
	
	/**
	 * 添加任务单
	 */
	@RequestMapping("/v1/save")
	//@RequiresPermissions("pp:proWork:save")
	public Result save(@RequestBody TaskListForm form) {
		
		if(StringUtil.isNullOrEmpty(form.getProjectId())) {
			return Result.error("项目不能为空！");
		}
		if(StringUtil.isNullOrEmpty(form.getProplanUniques())) {
			return Result.error("项目内容不能为空！");
		}		
		form.setInitailBy(getUserId());
//		ReturnFrom re = isDataQualified(form.getProplanUniques());	//验证数据是否合格
//		if("500".equals(re.getCode())) {
//			return Result.error(re.getMsg());
//		}
		try{
			proWorkOrderService.workSave(form);
			return Result.ok();
		}catch(Exception e){
			logger.error("任务单保存异常:",e);
			return Result.error(e.getMessage());
		}
		
	}
	
	/*@RequestMapping("/v1/update")
	@RequiresPermissions("bs:proWork:update")
	public Result update(@RequestBody ProWorkOrderEntity proWork) {		
		proWorkOrderService.updateById(proWork);
		return Result.ok();
	}*/
	
//	private ReturnFrom isDataQualified(String[] proplanUniques) {
//		ReturnFrom from = new ReturnFrom();		
//		for(String proplanUnique:proplanUniques) {
//			EntityWrapper<ProPlanDetail> ew = new EntityWrapper<ProPlanDetail>();
//			ew.eq("unique_id", proplanUnique);
//			ProPlanDetail ent = proPlanDetailService.selectOne(ew);
//			
//			EntityWrapper<ProContractPlandetail> cpEw = new EntityWrapper<ProContractPlandetail>();
//			ew.eq("con_id", ent.getConsplitId());
//			ew.eq("proplan_unique", proplanUnique);
//			ProContractPlandetail pro = proContractPlandetailService.selectOne(cpEw);
//			if(StringUtil.isNullOrEmpty(pro.getGross())|| StringUtil.isNullOrEmpty(pro.getSquare())) {
//				from.setCode("500");
//				from.setMsg("生产计划 名称为：" + ent.getWbsName() +"的生产计划的钢筋总吨数或每平米含量为空，无法生产任务单，请先补充数据！");				
//				return from;
//			}
//		}
//		from.setCode("200");
//		return from;
//	}
	
	
	/**
	 * 任务单作废
	 */
	@RequestMapping("/v1/delete")
	//@RequiresPermissions("pp:proWork:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
//		ProWorkOrderEntity ent = proWorkOrderService.selectById(id);
//		if("1".equals(ent.getWorkStatus())||"2".equals(ent.getWorkStatus())) {
//			proWorkOrderService.deleteById(id);
//			return Result.ok();
//		}else {
//			return Result.error("只能对  待开始、进行中  的任务单作废!");
//		}
		try{
			proWorkOrderService.cancelWork(id);
			return Result.ok();
		}catch(BusinessException e1){
			logger.error("任务单作废失败:",e1);
			return Result.error(e1.getMessage());
		}catch(Exception e){
			logger.error("任务单作废失败:",e);
			return Result.error("任务单作废失败");
		}
	}
}
