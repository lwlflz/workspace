package com.build.cloud.modules.punch.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.punch.bean.PunchBean;
import com.build.cloud.modules.punch.entity.ProAttendEntity;

/**
 * 
 * @ClassName: IProAttendService   
 * @Description: 考勤表
 * @author: liutao
 * @date: 2018年5月17日 上午11:05:55
 */
public interface IProAttendService extends IService<ProAttendEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    public void handlePunch();
    
    public Map<String, Object> queryMaxDate();

    Map<String, Object> queryCrossDay(Map<String, Object> param);
}

