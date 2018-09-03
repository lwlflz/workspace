package com.build.cloud.modules.sys.dao;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.vo.EmployeeVo;
/**
 * @ClassName: SysEmployeeDao
 * @Description: 员工信息表
 * @author: liutao
 * @date: 2018年3月31日 上午9:51:12
 */
public interface SysEmployeeDao extends BaseMapper<SysEmployeeEntity> {
	
	public List<EmployeeVo> selectPageByVo(Page<?> page, Map<String, Object> params);
	
	public int selectByCount(Map<String, Object> params);
}
