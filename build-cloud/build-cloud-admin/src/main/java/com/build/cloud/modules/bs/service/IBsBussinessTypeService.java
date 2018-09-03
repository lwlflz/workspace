package com.build.cloud.modules.bs.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.bs.entity.BsBussinessTypeEntity;

public interface IBsBussinessTypeService  extends IService<BsBussinessTypeEntity> {
	PageUtils queryPage(Map<String, Object> params);
}
