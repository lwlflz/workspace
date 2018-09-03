package com.build.cloud.modules.person.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.person.service.IPsnReportService;
import com.build.cloud.modules.productplan.service.IProContractService;

import cn.hutool.core.map.MapUtil;

/**
 * <p>
 *   劳务班组报表
 * </p>
 * @author qindq
 * @since 2018-06-7
 */
@Controller
@RequestMapping("/person/tc/report")
public class PsnReportController extends AbstractController {
    
    @Autowired
    private IPsnReportService psnReportService;
    
    @Autowired 
    private IProContractService proContractService;
    /**
     * 劳务班组总首页：1、根据项目查询班组执行情况
     * @param params
     * @return
     */
    @GetMapping("/getWorkTeam")
   	@ResponseBody
   	public Result getWorkTeamInfoByPro(@RequestParam Map<String, Object> params) {
   		try {
   			if(StringUtils.isEmpty(MapUtil.getStr(params, "projectId"))){
   				params.put("companyId", getCurrentCompanyId());
//   				return Result.error("项目ID不能为空");
   			}
   			//userId
   			//http://localhost:8080/dhjs/person/tc/report/getWorkTeam?projectId=1006698621715877889
   			
   			PageUtils page = psnReportService.getWorkTeamInfoByPro(params);
   			return Result.ok().put(page);
   		} catch (Exception e) {
   			logger.error("查询异常", e);
   			return Result.error("查询异常："+e.getMessage());
   		}
   	}
    
    /**
     * 劳务班组总首页：2、根据项目查询单项数据
     * @param params
     * @return
     */
    @GetMapping("/getItemValueByPro")
   	@ResponseBody
   	public Result getItemValueByPro(@RequestParam Map<String, Object> params) {
   		try {
   			if(StringUtils.isEmpty(MapUtil.getStr(params, "projectId"))){
   				return Result.error("项目ID不能为空");
   			}
   			//http://localhost:8080/dhjs/person/tc/report/getItemValueByPro?projectId=1006698621715877889
   			
   			//List<Map<String,Object>> map = psnReportService.getItemValueByPro(params);
   			
   			Map<String, Object> map = proContractService.getItemValueProperty(params);
   			return Result.ok().put(map);
   		} catch (Exception e) {
   			logger.error("查询异常", e);
   			return Result.error("查询异常："+e.getMessage());
   		}
   	}
    
    
}
