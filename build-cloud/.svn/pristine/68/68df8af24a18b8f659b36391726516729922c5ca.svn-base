package com.build.cloud.modules.person.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.person.dto.WorkTeamInfo;

/**
 * <p>
 *   劳务班组报表
 * </p>
 * @author qindq
 * @since 2018-05-27
 */
public interface PsnReportMapper extends BaseMapper<WorkTeamInfo> {
	
	List<WorkTeamInfo> getWorkTeamInfoByPro(Page<WorkTeamInfo> page,WorkTeamInfo wt);
	
	List<WorkTeamInfo> getWorkTeamInfoByTList(List<String> teamIdList);
	
	List<WorkTeamInfo> getBuildNameByPro(Map<String, Object> userMap);
	
	List<WorkTeamInfo> getWorkRecordDaysByTeam(Map<String, Object> params);
	
	
	List<Map<String,Object>> getItemValueByPro(Map<String, Object> params);

	
}