package com.build.cloud.modules.punch.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.punch.entity.StatCardEntity;

/**
 * 考勤记录表
 *
 * @author liutao
 * @date 2018-05-21 20:33:31
 */
public interface IStatCardService extends IService<StatCardEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 查询员工是否有考勤记录
     * @param params
     * @return
     */
    Integer selectWorkerStatCard(Map<String, Object> params);
}

