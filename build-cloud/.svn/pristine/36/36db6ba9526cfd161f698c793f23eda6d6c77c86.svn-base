package com.build.cloud.modules.person.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.person.dto.WorkTeamInfo;

/**
 * <p>
 *   劳务班组报表
 * </p>
 * @author qindq
 * @since 2018-05-27
 */
public interface IPsnReportService extends IService<WorkTeamInfo> {
	
	/**
     * 劳务班组总首页：1、根据项目查询班组执行情况
     * @param params
     * @return
     */
	PageUtils getWorkTeamInfoByPro(Map<String, Object> params);
	
	/**
     * 劳务班组总首页：2、根据项目查询单项数据
     * @param params
     * @return
     */
	List<Map<String,Object>> getItemValueByPro(Map<String, Object> params);
	
}
