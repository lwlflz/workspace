package com.build.cloud.modules.sys.controller;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.fastdfs.service.IFileService;
import com.build.cloud.modules.sys.entity.SysFileEntityEntity;
import com.build.cloud.modules.sys.entity.SysFileUploadEntity;
import com.build.cloud.modules.sys.service.ISysFileEntityService;
import com.build.cloud.modules.sys.service.ISysFileUploadService;
import com.sunsine.fastdfs.exception.FastDFSException;
/**
 * @ClassName: SysFileUploadController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: liutao
 * @date: 2018年4月9日 上午11:23:53
 */
@RestController
@RequestMapping("sys/file")
public class SysFileUploadController extends AbstractController {
	@Autowired
	private ISysFileUploadService sysFileUploadService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private ISysFileEntityService sysFileEntityService;

	/**
	 * @Title: list @Description: TODO(这里用一句话描述这个方法的作用) @param @param params @param @return
	 *         设定文件 @return Result 返回类型 @throws
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:file:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysFileUploadService.queryPage(params);
		return Result.ok().put(page);
	}
	/**
	 * @Title: info @Description: TODO(这里用一句话描述这个方法的作用) @param @param id @param @return 设定文件 @return
	 *         Result 返回类型 @throws
	 */
	@RequestMapping("/v1/info/{id}")
	@RequiresPermissions("sys:file:info")
	public Result info(@PathVariable("id") String id) {
		SysFileUploadEntity sysFileUpload = sysFileUploadService.selectById(id);
		return Result.ok().putEntity(sysFileUpload);
	}
	/**
	 * 
	 * @Title: save   
	 * @Description: 文件上传
	 * @param @param request
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@PostMapping("/v1/upload")
//	@RequiresPermissions("sys:file:save")
	public Result save(HttpServletRequest request) {
		return sysFileUploadService.upload(request);
	}
	
	/**
	 * @Title: update @Description: TODO(这里用一句话描述这个方法的作用) @param @param sysFileUpload @param @return
	 *         设定文件 @return Result 返回类型 @throws
	 */
	@RequestMapping("/v1/update")
	@RequiresPermissions("sys:file:update")
	public Result update(@RequestBody SysFileUploadEntity sysFileUpload) {
		sysFileUploadService.updateById(sysFileUpload);
		return Result.ok();
	}
	/**
	 * @Title: delete @Description: TODO(这里用一句话描述这个方法的作用) @param @param ids @param @return
	 *         设定文件 @return Result 返回类型 @throws
	 */
	@RequestMapping("/v1/delete")
	@RequiresPermissions("sys:file:delete")
	public Result delete(@RequestBody String[] ids) {
		sysFileUploadService.deleteBatchIds(Arrays.asList(ids));
		return Result.ok();
	}
}
