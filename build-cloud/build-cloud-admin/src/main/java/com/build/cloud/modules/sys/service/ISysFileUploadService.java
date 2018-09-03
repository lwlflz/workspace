package com.build.cloud.modules.sys.service;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.entity.SysFileEntityEntity;
import com.build.cloud.modules.sys.entity.SysFileUploadEntity;
/**
 * @ClassName: ISysFileUploadService
 * @Description: 文件上传表
 * @author: liutao
 * @date: 2018年4月3日 上午11:37:40
 */
public interface ISysFileUploadService extends IService<SysFileUploadEntity> {
	public PageUtils queryPage(Map<String, Object> params);
	public String save(SysFileUploadEntity sysFileUpload, SysFileEntityEntity fileEntityEntity);
	
	public Result upload(HttpServletRequest request);
}
