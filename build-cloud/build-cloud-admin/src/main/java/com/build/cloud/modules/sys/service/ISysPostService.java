package com.build.cloud.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.entity.SysPostEntity;

import java.util.Map;

/**
 * 
 * @ClassName: ISysPostService   
 * @Description: 员工岗位表
 * @author: liutao
 * @date: 2018年3月31日 上午9:51:48
 */
public interface ISysPostService extends IService<SysPostEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

