package com.build.cloud.modules.bs.service;

import java.util.List;

import com.build.cloud.core.base.service.IBaseService;
import com.build.cloud.modules.bs.entity.BsDynamicLogEntity;

public interface IBsDynamicLogService extends IBaseService<BsDynamicLogEntity>{

	public List<BsDynamicLogEntity> findBsDynamicLogList(String taskId);
}
