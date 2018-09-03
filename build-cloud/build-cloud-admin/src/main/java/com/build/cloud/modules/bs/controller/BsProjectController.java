/**
 * 
 */
package com.build.cloud.modules.bs.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.entity.OpPublicProjectEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.service.IDevDeviceService;
import com.build.cloud.modules.sys.entity.SysCompanyProjectEntity;
import com.build.cloud.modules.sys.service.ISysCompanyProjectService;
import com.google.common.collect.Maps;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;


/**
 * @className BsProjectFileController
 * @descripion 项目档案控制层
 * @author huangchao
 * @date 2018年4月11日下午3:31:39
 */
@RestController
@RequestMapping("/bs/project")
public class BsProjectController extends AbstractController{

	@Autowired
	private IBsProjectService bsProjectService;
	@Autowired
	private ISysCompanyProjectService sysCompanyProjectService;
	
	@Autowired
	private IDevDeviceService devDeviceService;

	
	@GetMapping("/v1/publicprojectlist")
	public Result pulicprojectlist(@RequestParam Map<String, Object> params) {
		List<OpPublicProjectEntity> page = bsProjectService.queryList(params);
		return Result.ok().put("result",page);
	}
	/**
	 * 项目档案 --列表分页
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("bs:project:list")
	public Result list(@RequestParam Map<String, Object> params){
		params.put("companyId", getCurrentCompanyId());
		PageUtils page = bsProjectService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 根据用户ID获取用户所有公司所有项目
	 */
	@GetMapping("/v1/getProjectListByCompanyId")
	@RequiresPermissions("bs:project:list")
	public Result getProjectListByCompanyId(String companyId){
		if(StrUtil.isBlank(companyId)){
			return Result.error("公司ID不能为空");
		}
		List<BsProjectEntity> list = bsProjectService.getProjectListByCompanyId(companyId);
		return Result.ok().putList(list);
	}
	
	
	/**
	 * 获取树形菜单
	 */
	@RequestMapping("/v1/getProjectTree")
	@RequiresPermissions("bs:project:list")
	public Result getProjectTree() {
		EntityWrapper<BsProjectEntity> wrApper = new EntityWrapper<BsProjectEntity>();
		wrApper.eq("status", "0");
		List<BsProjectEntity> projectList = bsProjectService.selectList(wrApper);
		return Result.ok().putList(projectList);
	}
	
	/**
	 * 项目档案 --信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("bs:project:info")
	public Result info(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		Map<String, Object> params = Maps.newHashMap();
		params.put("id", id);
		List<Map<String, Object>> list = bsProjectService.getProById(params);
		Map<String, Object> rmap = list.size() > 0 ? list.get(0) : Maps.newHashMap();
		return Result.ok().put(rmap);
	}
	
	/**
	 * 根据项目ID获取项目信息
	 */
	@GetMapping("/v1/findProjectByIds")
	@RequiresPermissions("bs:project:list")
	public Result findProjectByIds(String ids){
//		JSONArray objArray = JSONArray.parseArray(ids);
//		if(CollectionUtil.isEmpty(objArray)){
//			return Result.error("ID不能为空");
//		}
//		EntityWrapper<BsProjectEntity> wrapper = new EntityWrapper<BsProjectEntity>();
//		wrapper.in("id", ids);
//		List<BsProjectEntity> list = bsProjectService.selectList(wrapper);
		if(StrUtil.isBlank(ids)){
			return Result.error("ids不能为空");
		}
		List<String> paramlist = Arrays.asList(ids.split(","));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", paramlist);
		List<Map<String, Object>> list = bsProjectService.getProById(params);
		return Result.ok().putList(list);
	}
	
	/**
	 * 项目档案 --保存
	 */
	@RequestMapping("/v1/save")
	@RequiresPermissions("bs:project:save")
	@Transactional
	public Result save(@RequestBody BsProjectEntity entity){
		try {
			
			ValidatorUtils.validateEntity(entity);
			checkData(entity,1);//校验参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("project_code", entity.getProjectCode());
			map.put("project_name", entity.getProjectName());
			List<BsProjectEntity> projectFileList = bsProjectService.selectByMap(map);
			if(!CollectionUtil.isEmpty(projectFileList)){
				return Result.error("记录已经存在请检查后重试");
			}
			entity.setCompanyId(getCurrentCompanyId());
			if(!StrUtil.isBlank(entity.getProId())) {
				int num = bsProjectService.selectCount(new EntityWrapper<BsProjectEntity>().eq("pro_id", entity.getProId()).eq("company_id", getCurrentCompanyId()));
				if(num > 0) {
					return Result.error("已有项目与公共项目进行关联");
				}
			}
			bsProjectService.insert(entity);
			
			List<DevDeviceEntity> devDevice = entity.getDevDeviceParam();
			for(DevDeviceEntity deEntity : devDevice) {
				ValidatorUtils.validateEntity(deEntity,AddGroup.class);
				deEntity.setProjectId(entity.getId());	
			}
			if(CollectionUtil.isNotEmpty(devDevice)){
				devDeviceService.insertBatch(devDevice);
			}
			return Result.ok();
		} catch (Exception e) {
			logger.error("保存项目档案异常", e);
			return Result.error("保存项目档案失败"+e.getMessage());
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/v1/delete")
	@RequiresPermissions("bs:project:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		List<SysCompanyProjectEntity> list = sysCompanyProjectService.selectByMap(new HashMap<String, Object>());
		if(!CollectionUtil.isEmpty(list)){
			for (SysCompanyProjectEntity sysCompanyProjectEntity : list) {
				String[] projectids = sysCompanyProjectEntity.getProjectIds().split(",");
				for (String projectid : projectids) {
					if(id.equals(projectid)){
						return Result.error("项目已与角色进行关联，请检查!");
					}
				}
			}
		}
		//TODO 查询是否有关联信息 --查生产计划表
		int count = bsProjectService.getPlanCiteProject(id);
		if(count > 0) {
			return Result.error("该项目已经与生产计划进行关联不能进行删除，请检查");
		}
		bsProjectService.deleteById(id);
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/v1/update")
	@RequiresPermissions("bs:project:update")
	@Transactional
	public Result update(@RequestBody BsProjectEntity entity) {
		try {
			EntityWrapper<DevDeviceEntity> wrapper = new EntityWrapper<>();
			wrapper.eq("project_id", entity.getId());
			devDeviceService.delete(wrapper);
			List<DevDeviceEntity> devDevice = entity.getDevDeviceParam();
//			
			for(DevDeviceEntity deEntity : devDevice) {
				ValidatorUtils.validateEntity(deEntity,AddGroup.class);
				deEntity.setProjectId(entity.getId());
			}
			
			if (CollectionUtil.isNotEmpty(devDevice)) {
				devDeviceService.insertBatch(devDevice);
			}
			
			ValidatorUtils.validateEntity(entity);
			checkData(entity,2);//校验参数
//			/*************判断是否封存************************/
//			if(entity.getStatus().equals("1")){
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("parent_id", entity.getId());
//				map.put("status", "0");
//				List<BsProjectEntity> projectFileList = bsProjectService.selectByMap(map);
//				if (!CollectionUtil.isEmpty(projectFileList)) {
//					return Result.error("请先封存关联数据");
//				}
//			}
//			/************************************************/
			if("".equals(entity.getProId())) {
				entity.setProId(null);
			}
			bsProjectService.updateAllColumnById(entity);
			return Result.ok();
		} catch (Exception e) {
			logger.error("修改项目档案异常", e);
			return Result.error("修改项目档案失败"+e.getMessage());
		}
		
	}
	
	/**
	 * 校验参数
	 * @param entity 项目实体对象
	 * @param mark 标识 1,新增 2修改
	 */
	private void checkData(BsProjectEntity entity,int mark){
		if(mark == 2 && StrUtil.isBlank(entity.getId())){
			throw new BusinessException("ID不能为空");
		}
		if(StrUtil.isBlank(entity.getProjectCode())){
			throw new BusinessException("项目编码不能为空");
		}
		if(StrUtil.isBlank(entity.getProjectName())){
			throw new BusinessException("项目名称不能为空");
		}
//		if(StrUtil.isBlank(entity.getLeader())){
//			throw new BusinessException("项目负责人不能为空");
//		}
//		if(StrUtil.isBlank(entity.getProjectDate().toString())){
//			throw new BusinessException("项目立项时间不能为空");
//		}
//		if(StrUtil.isBlank(entity.getProjectOwner())){
//			throw new BusinessException("项目建设方不能为空");
//		}
//		if(StrUtil.isBlank(entity.getSupervisor())){
//			throw new BusinessException("项目监理方不能为空");
//		}
//		if(StrUtil.isBlank(entity.getDesigner())){
//			throw new BusinessException("项目设计方不能为空");
//		}
//		if(StrUtil.isBlank(entity.getStructureType())){
//			throw new BusinessException("项目结构类型不能为空");
//		}
	}
}
