package com.build.cloud.modules.punch.service;
import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
/**
 * @ClassName: IProAttendUserService
 * @Description: liutao
 * @author: liutao
 * @date: 2018年5月12日 上午11:11:28
 */
public interface IProAttendUserService extends IService<ProAttendUserEntity> {
	
	public boolean save();
	
}
