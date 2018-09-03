package com.build.cloud.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.vo.EmployeeVo;

import java.util.Map;

/**
 * 
 * @ClassName: ISysEmployeeService   
 * @Description: 员工信息表
 * @author: liutao
 * @date: 2018年3月31日 上午9:51:57
 */
public interface ISysEmployeeService extends IService<SysEmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    void insertEmployeeVo(EmployeeVo vo);
    
    boolean updateEmployeeVo(EmployeeVo vo);
    
    void deleteUserInfo(SysEmployeeEntity empEnt);
}

