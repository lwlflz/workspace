package com.build.cloud.modules.sys.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;
/**
 * @ClassName: SysDictService
 * @Description: 数据字典
 * @author: liutao
 * @date: 2018年3月16日 下午5:38:05
 */
public interface ISysDictService extends IService<SysDictEntity> {
	PageUtils queryPage(Map<String, Object> params);
	
	public Map<String, List<DictBean>> selectAll();
}
