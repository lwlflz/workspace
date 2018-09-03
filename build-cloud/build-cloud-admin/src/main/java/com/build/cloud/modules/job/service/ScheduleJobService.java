package com.build.cloud.modules.job.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.job.entity.ScheduleJobEntity;

import java.util.Map;
/**
 * @ClassName: ScheduleJobService
 * @Description: 定时任务
 * @author: liutao
 * @date: 2018年3月16日 下午2:46:08
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {
	PageUtils queryPage(Map<String, Object> params);
	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobEntity scheduleJob);
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(String[] jobIds);
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(String[] jobIds, int status);
	/**
	 * 立即执行
	 */
	void run(String[] jobIds);
	/**
	 * 暂停运行
	 */
	void pause(String[] jobIds);
	/**
	 * 恢复运行
	 */
	void resume(String[] jobIds);
}
