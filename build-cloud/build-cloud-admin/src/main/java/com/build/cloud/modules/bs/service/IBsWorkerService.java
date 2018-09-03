package com.build.cloud.modules.bs.service;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.exception.ServiceException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.modules.bs.bean.BsWorkerBean;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
/**
 * @ClassName: IBsWorkerService
 * @Description: 劳务用工
 * @author: liutao
 * @date: 2018年4月13日 下午3:27:01
 */
public interface IBsWorkerService extends IService<BsWorkerEntity> {
	public PageUtils queryPage(Map<String, Object> params);
	public void save(BsWorkerEntity workerEntity, SysUserEntity userEntity);
	
	public void update(BsWorkerEntity workerEntity, SysUserEntity userEntity);
	
	public List<Map<String, Object>> selectWorker(Map<String, Object> params);
	
	public List<BsWorkerBean> getInfoByIdCard(Map<String, Object> params) throws ServiceException;
}
