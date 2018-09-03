package com.build.cloud.modules.punch.service;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.punch.bean.PunchBean;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
/**
 * @ClassName: IDevDeviceService
 * @Description: 考勤机
 * @author: liutao
 * @date: 2018年5月17日 上午11:05:31
 */
public interface IDevDeviceService extends IService<DevDeviceEntity> {
	PageUtils queryPage(Map<String, Object> params);
	public void syncDev(Map<String, Object> params);
	public List<Map<String, Object>> queryDevEmp(Map<String, Object> params);
	public List<String> selectAllProject();
}
