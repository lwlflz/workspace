package com.build.cloud.modules.sys.controller;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.bean.UserComBean;
import com.build.cloud.modules.sys.entity.SysCompanyEntity;
import com.build.cloud.modules.sys.entity.SysDeptEntity;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.service.ISysCompanyService;
import com.build.cloud.modules.sys.service.ISysDeptService;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.google.common.collect.Maps;
import com.sunsine.common.util.CollectionUtil;

import cn.hutool.core.util.PinyinUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysDeptController
 * @Description: 部门管理
 * @author: liutao
 * @date: 2018年3月16日 下午1:56:10
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
	
	// 30天后过期
	private final static long EXPIRE = 3600 * 24 * 30;
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private ISysCompanyService sysCompanyService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	/**
	 * 列表
	 */
	@RequestMapping("/v1/list")
	@RequiresPermissions("sys:dept:list")
	public Result list() {
		return Result.ok().putList(selectDeptList());
	}
	
	private List<SysDeptEntity> selectDeptList() {
		Map<String, Object> params = Maps.newHashMap();
		params.put("corpCode", getUser().getCorpCode());
		params.put("companyId", getCurrentCompanyId());
		List<SysDeptEntity> deptList = sysDeptService.queryList(params);
		return deptList;
	}
	
	/**
	 * 根据部门Id查询部门信息组成树形结构
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/getDeptTreeByDeptId")
	@RequiresPermissions("sys:dept:list")
	public Result getDeptTreeByDeptId(@RequestParam(required = false) String companyId){
		UserComBean result = redisUtils.get(getUserId(),UserComBean.class);
		if(!StrUtil.isBlank(companyId)){
			if(result != null && !companyId.equals(result.getCompanyId())){
				redisUtils.delete(RedisKeys.getDefCom(getUserId()));
				result = null;
			}
		}
		if(result == null) {
			result = new UserComBean();
			result.setDeptList(sysDeptService.getDeptTreeByCompanyId(companyId)); 
			result.setCompanyId(companyId);
			result.setUserId(getUserId());
			redisUtils.set(RedisKeys.getDefCom(getUserId()), result, EXPIRE);
		}
		return Result.ok().putList(result.getDeptList());
	}
	
	/**
	 * 输入条件模糊检索部门信息
	 * @param deptName 检索条件
	 * @return
	 */
	@RequestMapping("/v1/getDeptByDeptName")
	@RequiresPermissions("sys:dept:list")
	public Result getDeptByDeptName(String deptName){
		List<Map<String, Object>> deptList = sysDeptService.getDeptByDeptName(deptName);
		return Result.ok().putList(deptList);
	}
	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/v1/select")
	@RequiresPermissions("sys:dept:select")
	public Result select() {
		return Result.ok().putList(selectDeptList());
	}

	/**
	 * 信息
	 * @param id
	 * @return
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("sys:dept:info")
	public Result info(String id) {
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		SysDeptEntity dept = sysDeptService.selectById(id);
		if(dept != null){
			SysDeptEntity entity = sysDeptService.selectById(dept.getParentId());
			if(entity != null){
				dept.setParentName(entity.getDeptName());
			}
			SysCompanyEntity companyEntity = sysCompanyService.selectById(dept.getCompanyId());
			if(companyEntity != null){
				dept.setCompanyName(companyEntity.getCompanyName());
			}
		}
		return Result.ok().putEntity(dept);
	}
	/**
	 * 保存
	 */
	@RequestMapping("/v1/save")
	@RequiresPermissions("sys:dept:save")
	public Result save(@RequestBody SysDeptEntity dept) {
		paramVerifi(dept,1);//校验参数是否为空
		if(!StrUtil.isBlank(dept.getParentId())){
			Map<String, Object> map = Maps.newHashMap();
			map.put("id",dept.getParentId());
			map.put("status", "1");
			List<SysDeptEntity> deptList = sysDeptService.selectByMap(map);
			map.clear();
			map.put("dept_code", dept.getDeptCode());
			map.put("dept_name", dept.getDeptName());
			map.put("company_id", dept.getCompanyId());
			if(!CollectionUtil.isEmpty(sysDeptService.selectByMap(map))){
				return Result.error("记录已经存在，请检查");
			}
			if(!CollectionUtil.isEmpty(deptList)){
				return Result.error("上级部门'"+deptList.get(0).getDeptName()+"'已经被封存,不能被引用，请检查后重试");
			}
			if(!dept.getParentId().equals("0")){
				map.clear();
				map.put("parent_id",dept.getId());
				map.put("status", "0");
				List<SysDeptEntity> parentList = sysDeptService.selectByMap(map);
				if(parentList.size() > 0){
					return Result.error("请先封存相关子集，再进行封存");
				}
			}
			
		}
		dept.setPinyinCode(PinyinUtil.getAllFirstLetter(dept.getDeptName()));
		dept.setCorpCode(getUser().getCorpCode());
		dept.setCorpName(getUser().getCorpName());
		dept.setCreateBy(getUser().getLoginName());
		sysDeptService.insert(dept);
		/********************记录变更更新缓存记录*******************************/
//		UserComBean result = redisUtils.get(getUserId(),UserComBean.class);
//		if(result != null){
//			redisUtils.delete(getUserId());
//			result.setDeptList(sysDeptService.getDeptTreeByCompanyId(result.getCompanyId())); 
//			redisUtils.set(getUserId(), result, EXPIRE);
//		}
		/********************************************************************/
		return Result.ok().putObject("id", dept.getId());
	}
	/**
	 * 修改
	 */
	@RequestMapping("/v1/update")
	@RequiresPermissions("sys:dept:update")
	public Result update(@RequestBody SysDeptEntity dept) {
		paramVerifi(dept,2);//校验参数是否为空
		Map<String, Object> deptMap = Maps.newHashMap();
		deptMap.put("corp_code", getCorpCode());
		deptMap.put("parent_id", dept.getId());
		deptMap.put("valid", 0);
		deptMap.put("status", 0);
		SysDeptEntity deptEntity = sysDeptService.selectById(dept.getId());
		if(!dept.getParentId().equals(deptEntity.getParentId())){
			List<SysDeptEntity> deptChildList = sysDeptService.selectByMap(deptMap);
			if(deptChildList.size() > 0) {
				return Result.error("如要变更上级部门，请先移除子级部门再进行操作");
			}
		}
		dept.setPinyinCode(PinyinUtil.getAllFirstLetter(dept.getDeptName()));
		if(dept.getId().equals(dept.getParentId())){
			return Result.error("上级部门选择错误，请重新选择上级部门！");
		}
		sysDeptService.updateById(dept);
		/********************记录变更更新缓存记录*******************************/
//		UserComBean result = redisUtils.get(getUserId(),UserComBean.class);
//		if(result != null){
//			redisUtils.delete(getUserId());
//			result.setDeptList(sysDeptService.getDeptTreeByCompanyId(result.getCompanyId())); 
//			redisUtils.set(getUserId(), result, EXPIRE);
//		}
		/********************************************************************/
		return Result.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("sys:dept:delete")
	public Result delete(@RequestBody String id) {
		// 判断是否有子部门
		id = getDelId(id);
		if(StrUtil.isEmpty(id)){
			return Result.error("ID不能为空");
		}
		EntityWrapper<SysDeptEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("parent_id", id);
		int deptCount = sysDeptService.selectCount(wrapper);
		if (deptCount > 0) {
			return Result.error("请先删除子部门");
		}
		
		int count = sysEmployeeService.selectCount(new EntityWrapper<SysEmployeeEntity>().eq("dept_id", id));
		if(count > 0){
			return Result.error("该部门已经与用户进行了关联,请先删除关联");
		}
		sysDeptService.deleteById(id);
		/********************记录变更更新缓存记录*******************************/
//		UserComBean result = redisUtils.get(getUserId(),UserComBean.class);
//		if(result != null){
//			redisUtils.delete(getUserId());
//			result.setDeptList(sysDeptService.getDeptTreeByCompanyId(result.getCompanyId())); 
//			redisUtils.set(getUserId(), result, EXPIRE);
//		}
		/********************************************************************/
		return Result.ok();
	}
	/**
	 * 验证参数
	 * @param entity 部门实体对象
	 * @param mark 标识, 1 保存调用 2,修改调用(区分是否传递ID)
	 */
	private void paramVerifi(SysDeptEntity entity,int mark){
		if(mark == 2 && StrUtil.isBlank(entity.getId())){
			throw new BusinessException("ID不能为空");
		}
		if(StrUtil.isBlank(entity.getDeptCode())){
			throw new BusinessException("部门编码不能为空");
		}
		if(StrUtil.isBlank(entity.getDeptName())){
			throw new BusinessException("部门名称不能为空");
		}
		if(StrUtil.isBlank(entity.getLeader())){
			throw new BusinessException("部门负责人不能为空");
		}
		if(StrUtil.isBlank(entity.getPhone())){
			throw new BusinessException("负责人电话不能为空");
		}
		
	}
	
}
