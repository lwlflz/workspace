package com.build.cloud.modules.punch.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.build.cloud.modules.punch.bean.PunchBean;
import com.build.cloud.modules.punch.entity.ProAttendEntity;

/**
 * 
 * @ClassName: ProAttendDao   
 * @Description: 考勤表
 * @author: liutao
 * @date: 2018年5月17日 上午10:43:54
 */
public interface ProAttendDao extends BaseMapper<ProAttendEntity> {
	
	public Map<String, Object> queryMaxDate();

	public Map<String, Object> queryCrossDay(Map<String, Object> param);
	
}
