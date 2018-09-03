package com.build.cloud.modules.productplan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.productplan.dto.ProContract;
import com.build.cloud.modules.productplan.dto.ProContractPayment;
import com.build.cloud.modules.productplan.dto.ProContractPlandetail;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.service.IProContractPaymentService;
import com.build.cloud.modules.productplan.service.IProContractPlandetailService;
import com.build.cloud.modules.productplan.service.IProContractService;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.util.DictUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author liangsen
 * @since 2018-04-23
 */
@Controller
@RequestMapping("/pp/proContract")
public class ProContractController extends AbstractController{
    @Autowired 
    private IProContractService proContractService;
    
    @Autowired
    private IProContractPlandetailService proContractPlandetailService;
    
    @Autowired
    private IProContractPaymentService contractPaymentService;
    
    /**
	 * 获取劳务班组合同所有类型
	 */
	@GetMapping("/v1/getConType")
	@ResponseBody
	public Result getWorkType() {
		List<DictBean> codeTypeList = DictUtils.getDictList("con_type");
		return Result.ok().putList(codeTypeList);
	}
    
    /**
     * 保存合同
     * @param info
     * @return
     */
    @PostMapping("/v1/save")
   	@ResponseBody
   	public Result insertProContract(@RequestBody ProContract proContract) {
   		try {
   			proContractService.insertProContract(proContract);
   			return Result.ok("保存成功").putObject("id", proContract.getId());
   		} catch (Exception e) {
   			logger.error("保存失败", e);
   			return Result.error("保存失败："+e.getMessage());
   		}
   	}
    
    /**
     * 修改合同
     * @param info
     * @return
     */
    @PostMapping("/v1/delete")
   	@ResponseBody
   	public Result dalete(@RequestBody String id) {
    	id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
   		try {
   			proContractService.deleteById(id);
   			contractPaymentService.delete(new EntityWrapper<ProContractPayment>().eq("con_id", id));
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("删除失败", e);
   			return Result.error("删除失败："+e.getMessage());
   		}
   	}
    
    /**
     * 修改合同
     * @param update
     * @return
     */
    @PostMapping("/v1/update")
   	@ResponseBody
   	public Result updateProContract(@RequestBody ProContract proContract) {
   		try {
   			proContractService.updateProContract(proContract);
   			return Result.ok("修改成功").put(proContract.getId());
   		} catch (Exception e) {
   			logger.error("修改失败", e);
   			return Result.error("修改失败："+e.getMessage());
   		}
   	}
    
    /**
     * 合同列表
     * @param info
     * @return
     */
    @GetMapping("/v1/list")
   	@ResponseBody
   	public Result selectProContract(@RequestParam Map<String, Object> params) {
   		try {
//   			proContract.setCompanyId(getCurrentCompanyId());//查询当前公司,数据权限控制
   			
   			PageUtils page = proContractService.selectConList(params,getCurrentCompanyId());
   			return Result.ok().put(page);
   		} catch (Exception e) {
   			logger.error("查询合同列表失败", e);
   			return Result.error("查询合同列表失败："+e.getMessage());
   		}
   	}
    
    /**
     * 钢筋班组首页：2、根据项目查询单项数据
     * @param params
     * @return
     */
    @GetMapping("/v1/getItemValueByPro")
   	@ResponseBody
   	public Result getItemValueByPro(@RequestParam Map<String, Object> params) {
   		try {
   			if(StringUtils.isEmpty(MapUtil.getStr(params, "projectId"))){
   				return Result.error("项目ID不能为空");
   			}
   			//http://localhost:8080/dhjs/pp/proContract/v1/getItemValueByPro?projectId=1006698621715877889
   			
   			//List<Map<String,Object>> map = proContractService.getItemValueByPro(params);
   			//获取整改率  整改完成率  任务单完成计数
   			Map<String,Object> map = proContractService.getItemValue(params);
   			return Result.ok().put(map);
   		} catch (Exception e) {
   			logger.error("查询异常", e);
   			return Result.error("查询异常："+e.getMessage());
   		}
   	}
    
    /**
     * 查询合同详情
     * @param info
     * @return
     */
    @GetMapping("/v1/info")
   	@ResponseBody
   	public Result selectById(ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			ProContract res = proContractService.selectProContractById(proContract);
   			return new Result().put(res);
   		} catch (Exception e) {
   			logger.error("查询合同详情失败", e);
   			return Result.error("查询合同详情失败："+e.getMessage());
   		}
   	}
    
    /**
     * 合同拆分展示
     * @param info
     * @return
     */
    @GetMapping("/v1/selectConSplit")
   	@ResponseBody
   	public Result selectConSplit(ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			if(StringUtils.isEmpty(proContract.getProjectId())){
   				return Result.error("项目id不能为空");
   			}
   			
   			ProContract selectInfo = proContractService.selectById(proContract.getId());
   			if(!Objects.equal("2", selectInfo.getCheckStatus())){
   				return Result.error("合同未完成审核,不能进行拆分");
   			}
   			
   			List<ProContractPlandetail> resList = proContractService.selectConSplit(proContract);
   			
   			return Result.ok().put(resList);
   		} catch (Exception e) {
   			logger.error("查询合同拆分展示失败", e);
   			return Result.error("查询合同拆分展示失败："+e.getMessage());
   		}
   	}
    
    /**
     * 合同拆分选择生产计划界面查询
     * @param info
     * @return
     */
    @GetMapping("/v1/selectConSplitPlan")
   	@ResponseBody
   	public Result selectConSplitPlan(ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			if(StringUtils.isEmpty(proContract.getProjectId())){
   				return Result.error("项目id不能为空");
   			}
   			List<ProPlanDetail> resList = proContractService.selectConSplitPlan(proContract);
   			
   			Map<String,List<ProPlanDetail>> map = Maps.newHashMap();
   			map.put("list", resList);
   			return Result.ok().put(map);
   		} catch (Exception e) {
   			logger.error("查询合同拆分选择生产计划失败", e);
   			return Result.error("查询合同拆分选择生产计划失败："+e.getMessage());
   		}
   	}
    
    /**
     * 查询合同产值
     * @param info
     * @return
     */
    @GetMapping("/v1/selectValue")
   	@ResponseBody
   	public Result selectValue(ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			if(StringUtils.isEmpty(proContract.getProjectId())){
   				return Result.error("项目id不能为空");
   			}
   			List<ProContractPlandetail> resList = proContractService.selectValue(proContract);
   			
   			Map<String, List<ProContractPlandetail>> map = Maps.newHashMap();
   			map.put("list", resList);
   			return Result.ok().put(map);
   		} catch (Exception e) {
   			logger.error("查询合同产值失败", e);
   			return Result.error("查询合同产值失败："+e.getMessage());
   		}
   	}
    
    /**
     * 合同拆分保存(可能无用)
     * @param info
     * @return
     */
    @PostMapping("/v1/saveConSplit")
   	@ResponseBody
   	public Result insertConSplit(@RequestBody ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			ProContract selectInfo = proContractService.selectById(proContract.getId());
   			if(!Objects.equal("2", selectInfo.getCheckStatus())){
   				return Result.error("合同未完成审核,不能进行拆分");
   			}
   			//判断是否已拆分
//   			ProContract conditionCon = proContractService.selectById(proContract.getId());
//   			if(Objects.equal("1", conditionCon.getSplitStatus())){
//   				return Result.error("此合同已拆分");
//   			}
   			
   			proContractService.insertConSplit(proContract);
   			return Result.ok("合同拆分成功");
   		} catch (Exception e) {
   			logger.error("合同拆分失败", e);
   			return Result.error("合同拆分失败："+e.getMessage());
   		}
   	}
    
    /**
     * 合同拆分修改数值保存
     * @param info
     * @return
     */
    @PostMapping("/v1/updateConSplitValue")
   	@ResponseBody
   	public Result updateConSplitValue(@RequestBody ProContract proContract) {
   		try {
   			if(StringUtils.isEmpty(proContract.getId())){
   				return Result.error("id不能为空");
   			}
   			
   			proContractService.saveConSplitValue(proContract);
   			return Result.ok("修改成功");
   		} catch (Exception e) {
   			logger.error("修改失败", e);
   			return Result.error("修改失败："+e.getMessage());
   		}
   	}
    
    /**
     * 
     * @param info
     * @return
     */
    @PostMapping("/v1/actSubmit")
   	@ResponseBody
   	public Result submit(@RequestBody ProContract proContract) {
   		try {
   			proContractService.submit(proContract);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("提交审核异常", e);
   			return Result.error("提交审核异常："+e.getMessage());
   		}
   	}
    
    /**
     * 
     * @param info
     * @return
     */
    @PostMapping("/v1/actCheck")
   	@ResponseBody
   	public Result check(@RequestBody ProContract proContract) {
   		try {
   			proContractService.check(proContract);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("审核异常", e);
   			return Result.error("审核异常："+e.getMessage());
   		}
   	}
    
    /**
     * 
     * @param info
     * @return
     */
    @PostMapping("/v1/actReject")
   	@ResponseBody
   	public Result reject(@RequestBody ProContract proContract) {
   		try {
   			proContractService.reject(proContract);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("驳回异常", e);
   			return Result.error("驳回异常："+e.getMessage());
   		}
   	}
    
    /**
     * 
     * @param info
     * @return
     */
    @PostMapping("/v1/actEndReturn")
   	@ResponseBody
   	public Result end(@RequestBody ProContract proContract) {
   		try {
   			EntityWrapper<ProContractPlandetail> detailEw = new EntityWrapper<>();
   			detailEw.eq("con_id", proContract.getId());
   			Integer count = proContractPlandetailService.selectCount(detailEw);
   			if(count>0){
   				return Result.error("合同已关联拆分,无法弃审！");
   			}
   			
   			proContractService.endReturn(proContract);
   			return Result.ok();
   		} catch (Exception e) {
   			logger.error("取消审批异常", e);
   			return Result.error("取消审批异常："+e.getMessage());
   		}
   	}
}
