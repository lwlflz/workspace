package com.build.cloud.modules.punch.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.Assert;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
import com.build.cloud.modules.punch.service.IDevDeviceService;
import com.build.cloud.modules.punch.service.IProAttendService;
import com.build.cloud.modules.punch.service.IProAttendUserService;
import com.build.cloud.modules.punch.service.IPunchService;
import com.build.cloud.modules.punch.service.ISyncService;
import com.google.common.collect.Maps;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
@RestController
@RequestMapping("/punch")
public class PunchController extends AbstractController {
	@Autowired
	private IProAttendUserService proAttendUserService;
	@Autowired
	private IDevDeviceService devDeviceService;
	@Autowired
	private ISyncService syncService;
	@Autowired
	private IProAttendService proAttendService;
	@Autowired
	private IPunchService punchService;
	
	/**
	 * 
	 * @Title: queryDevEmp   
	 * @Description: 考勤机人员
	 * @param @param params
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@GetMapping("/v1/queryDevEmp")
	public Result queryDevEmp(@RequestParam Map<String,Object> reqMap) {
		List<Map<String, Object>> list = devDeviceService.queryDevEmp(reqMap);
		return Result.ok().putList(list);
	}
	
	@GetMapping("/v1/queryAttendUser")
	public Result queryAttendUser(@RequestParam Map<String, Object> params) {
		EntityWrapper<ProAttendUserEntity> ew = new EntityWrapper<ProAttendUserEntity>();
		String szEmployId = MapUtil.getStr(params, "szEmployId");
		ew.like("sz_employ_id", szEmployId);
		ew.like("sz_name", MapUtil.getStr(params, "szName"));
		ew.orderBy("ts_create", false);
		List<ProAttendUserEntity> entitys = proAttendUserService.selectList(ew);
		return Result.ok().putList(entitys);
	}
	/**
	 * 
	 * @Title: getDev   
	 * @Description: 获取考勤机设备
	 * @param @param params
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@GetMapping("/v1/getDev")
	public Result getDev(@RequestParam Map<String, Object> params) {
		EntityWrapper<DevDeviceEntity> ew = new EntityWrapper<DevDeviceEntity>();
//		String projectId = MapUtil.getStr(params, "projectId");
//		if (StrUtil.isNotEmpty(projectId)) {
//			ew.ne("project_id", projectId);
//		}
		ew.isNull("project_id").or().eq("project_id", "");
		ew.orderBy("ts_create", false);
		List<DevDeviceEntity> entitys = devDeviceService.selectList(ew);
		return Result.ok().putList(entitys);
	}
//	@PostMapping("/v1/saveDev")
//	public Result saveDev(@RequestBody List<DevDeviceEntity> list) {
//		try {
//			for (DevDeviceEntity devDevice : list) {
//				ValidatorUtils.validateEntity(devDevice, AddGroup.class);
//			}
//			devDeviceService.insertBatch(list);
//			return Result.ok("保存成功");
//		} catch (Exception e) {
//			logger.error("考勤设备保存异常"+e);
//			return Result.error("保存失败"+e.getMessage());
//		}
//	}
	@PostMapping("/v1/updateDev")
	public Result updateDev(@RequestBody Map<String, Object> params) {
		Long ngId = MapUtil.getLong(params, "ngId");
		Assert.isNull(ngId, "考勤机设备ID不能为空");
		
		DevDeviceEntity entity = devDeviceService.selectById(ngId);
		String isIn = MapUtil.getStr(params, "isIn");
		if (StrUtil.isNotEmpty(isIn)) {
			entity.setIsIn(isIn);
		}
		String projectId = MapUtil.getStr(params, "projectId");
		if (StrUtil.isNotEmpty(projectId)) {
			entity.setProjectId(projectId);
		}
		
		boolean result = devDeviceService.updateById(entity);
		if (result) {
			return Result.ok("修改考勤机设备成功！");
		}
		return Result.error("修改考勤机设备失败！");
	}
	@PostMapping("/v1/delDev")
	public Result delDev(@RequestBody Map<String, Object> params) {
		Long ngId = MapUtil.getLong(params, "ngId");
		String projectId = MapUtil.getStr(params, "projectId");
		Assert.isNull(ngId, "考勤机设备ID不能为空");
		EntityWrapper<DevDeviceEntity> ew = new EntityWrapper<DevDeviceEntity>();
		ew.eq("ng_id", ngId);
		ew.eq("project_id", projectId);
		DevDeviceEntity entity = devDeviceService.selectOne(ew);
		entity.setProjectId("");
		
		boolean result = devDeviceService.updateAllColumnById(entity);
		if (result) {
			return Result.ok("修改考勤机设备成功！");
		}
		return Result.error("修改考勤机设备失败！");
	}
	@GetMapping("/v1/getDevList")
	public Result getDevList(@RequestParam String projectId) {
		List<DevDeviceEntity> devList = devDeviceService.selectList(new EntityWrapper<DevDeviceEntity>().eq("project_id", projectId));
		return Result.ok().putList(devList);
	}
	
	/**
	 * 
	 * @Title: queryPunch   
	 * @Description: 获取考勤信息   
	 * @param @param params
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@GetMapping("/v1/queryPunch")
	// @RequiresPermissions("punch:queryPunch")
	public Result queryPunch(@RequestParam Map<String, Object> params) {
		PageUtils page = proAttendService.queryPage(params);
		return Result.ok().put(page);
	}
	/**
	 * 
	 * @Title: syncPunch   
	 * @Description: 处理同步考勤时间
	 * @param @param params
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@GetMapping("/v1/syncPunch")
	// @RequiresPermissions("punch:queryPunch")
	public Result syncPunch(@RequestParam Map<String, Object> params) {
//		syncService.syncPunch();
		punchService.queryPunch(Maps.newHashMap());
		return Result.ok();
	}
	
	@GetMapping("/v1/handlePunch")
	// @RequiresPermissions("punch:handlePunch")
	public Result handlePunch() {
		proAttendService.handlePunch();
		return Result.ok();
	}
	
	@GetMapping("/v1/syncUser")
	// @RequiresPermissions("punch:syncUser")
	public Result syncUser() {
		syncService.syncUser();
		return Result.ok("考勤机用户数据同步成功！");
	}
	@GetMapping("/v1/syncDev")
	// @RequiresPermissions("punch:syncDev")
	public Result syncDev() {
		syncService.syncDev();
		return Result.ok("考勤机数据同步成功");
	}
	@GetMapping("/v1/syncStatcard")
	// @RequiresPermissions("punch:syncStatcard")
	public Result syncStatcard() {
		syncService.syncStatcard();
		return Result.ok("考勤数据同步成功");
	}
}
