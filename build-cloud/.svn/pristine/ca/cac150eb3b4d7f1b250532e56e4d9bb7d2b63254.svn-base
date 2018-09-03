package com.build.cloud.modules.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysRoleCompanyEntity;
import com.build.cloud.modules.sys.entity.SysRoleEntity;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;
import com.build.cloud.modules.sys.form.RoleCompanyForm;
import com.build.cloud.modules.sys.service.ISysCompanyProjectService;
import com.build.cloud.modules.sys.service.ISysMenuService;
import com.build.cloud.modules.sys.service.ISysRoleCompanyService;
import com.build.cloud.modules.sys.service.ISysRoleDeptService;
import com.build.cloud.modules.sys.service.ISysRoleMenuService;
import com.build.cloud.modules.sys.service.ISysRoleService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @ClassName: SysRoleController
 * @Description: 角色管理
 * @author: liutao
 * @date: 2018年3月16日 下午2:49:17
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	@Autowired
	private ISysRoleDeptService sysRoleDeptService;
	@Autowired
	private ISysRoleCompanyService sysRoleCompanyService;	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
	private ISysCompanyProjectService sysCompanyProjectService;
	/**
	 * 角色列表
	 */
	@RequestMapping("/v1/list")
	@RequiresPermissions("sys:role:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysRoleService.queryPage(params);
		@SuppressWarnings("unchecked")
		List<SysRoleEntity> list = (List<SysRoleEntity>) page.getList();		
		companySetRoleList(list);
		page.setList(list);		
		return Result.ok().put(page);
	}
	
	/**
	 * 角色所拥有的人员列表
	 */
	@RequestMapping("/v1/userRoleList")
	@RequiresPermissions("sys:role:list")
	public Result userRoleList(@RequestParam Map<String, Object> params) {
		PageUtils page = sysRoleService.queryUserRolePage(params);
		return Result.ok().put(page);
	}
	
	/**
	 * 获取角色已选公司ID
	 */
	@GetMapping("/v1/getpanybyroleid")
	@RequiresPermissions("sys:role:list")
	public Result getpanybyroleid(String roleId) {
		EntityWrapper<SysRoleCompanyEntity> wrapper = new EntityWrapper<SysRoleCompanyEntity>();
		wrapper.eq("role_id", roleId);
		List<SysRoleCompanyEntity> list = sysRoleCompanyService.selectList(wrapper);
		return Result.ok().putList(list);
	}
	
	/**
	 * 保存角色默认公司
	 */
	@SysLog("保存角色默认公司")
	@RequestMapping("/v1/saverolepany")
	@RequiresPermissions("sys:role:save")
	public Result saverolepany(@RequestBody String obj){
		JSONObject jsonobj = JSONObject.parseObject(obj);
		String roleId = jsonobj.getString("roleId");
		List<String> companyIds = jsonobj.parseArray(jsonobj.getString("companyIds"), String.class);
		sysRoleService.saverolepany(roleId, companyIds);
		return Result.ok();
	}
	
	
	
	/**
	 * 根据角色ID获取当前角色分配的用户列表（分页）
	 */
	@RequestMapping("/v1/findUserAndProjectByRoleId")
	@RequiresPermissions("sys:role:select")
	public Result findUserAndProjectByRoleId(@RequestParam Map<String, Object> params) {
		PageUtils page = sysRoleService.findUserAndProjectByRoleId(params);
		return Result.ok().put(page);
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/v1/select")
	@RequiresPermissions("sys:role:select")
	public Result select() {
		List<SysRoleEntity> list = sysRoleService.selectList(null);
		return Result.ok().putList(list);
	}

	/**
	 * 角色信息
	 */
	@RequestMapping("/v1/info/{id}")
	@RequiresPermissions("sys:role:info")
	public Result info(@PathVariable("id") String id) {
		if (StrUtil.isBlank(id)) {
			return Result.error("ID不能为空");
		}
		SysRoleEntity role = sysRoleService.selectById(id);
		// 查询角色对应的菜单
		List<String> menuIdList = sysRoleMenuService.queryMenuIdList(id);
		role.setMenuIdList(menuIdList);
		// 查询角色对应的部门
		List<String> deptIdList = sysRoleDeptService.queryDeptIdList(new String[] { id });
		role.setDeptIdList(deptIdList);
		// 查询角色对应的公司
		List<String> companyIdList = sysRoleCompanyService.queryCompanyIdList(new String[] { id });
		role.setCompanyIdList(companyIdList);
		return Result.ok().putEntity(role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@RequestMapping("/v1/save")
	@RequiresPermissions("sys:role:save")
	public Result save(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);
		paramVerifi(role, 1);
		EntityWrapper<SysRoleEntity> wrApper = new EntityWrapper<SysRoleEntity>();
		// 校验当前插入的数据判断 role_code 或者 role_name在数据库中已经存在，则不允许再插入数据
		wrApper.and().eq("role_code", role.getRoleCode()).or().eq("role_name", role.getRoleName());
		int ret = sysRoleService.selectCount(wrApper);
		if (ret > 0) {
			return Result.error("角色名称或者角色编码相同，请换一个名称或者编码 !");
		}
		role.setCorpCode(getCorpCode());
		sysRoleService.save(role);
		return Result.ok();
	}
	
	/**
	 * 项目分配
	 */
	@SysLog("项目分配")
	@RequestMapping("/v1/saveproject")
	@RequiresPermissions("sys:role:save")
	public Result saveproject(@RequestBody String json) {
		//ValidatorUtils.validateEntity(role);
		JSONObject obj = JSONObject.parseObject(json);
		String companyId = obj.getString("companyId");
		String userId = obj.getString("userId");
		List<String> projectIds = JSONArray.parseArray(obj.getString("projectIds"), String.class);
		sysCompanyProjectService.findPanyProjectByCompanyId(companyId,projectIds,userId);
		return Result.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@RequestMapping("/v1/update")
	@RequiresPermissions("sys:role:update")
	public Result update(@RequestBody SysRoleEntity role) {
		ValidatorUtils.validateEntity(role);
		paramVerifi(role, 2);
		sysRoleService.update(role);
		return Result.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@RequestMapping("/v1/delete")
	@RequiresPermissions("sys:role:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		SysRoleEntity role = sysRoleService.selectById(id);
		if (role != null && role.getIsSys().equals(Constant.SYS_ROLE)) {
			return Result.error("内置角色不允许删除！");
		}
		
		EntityWrapper<SysUserRoleEntity> ew = new EntityWrapper<SysUserRoleEntity>();
		ew.eq("role_id", id);		
		int ret = sysUserRoleService.selectCount(ew);
		if(ret > 0) {
			return Result.error("角色已分配人员，无法删除");
		}
		
		sysRoleService.deleteById(id);
		return Result.ok();
	}
	
	/**
	 * 删除用户角色关联
	 */
	@SysLog("删除用户角色关联")
	@RequestMapping("/v1/deleteuserrole")
	@RequiresPermissions("sys:role:delete")
	public Result deleteuserrole(@RequestBody String json) {
		JSONObject obj = JSONObject.parseObject(json);
		String userId = (String)obj.get("userId");
		String roleId = (String)obj.get("roleId");
		List<String> companyIds = obj.parseArray(obj.getString("companyIds"), String.class);
		for (String companyId : companyIds) {
			EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
			wrapper.eq("user_id", userId);
			wrapper.eq("role_id", roleId);
			wrapper.eq("company_id", companyId);
			sysUserRoleService.delete(wrapper);
		}
		return Result.ok();
	}

	/**
	 * 参数校验
	 * 
	 * @param entity
	 *            角色实体对象
	 * @param mark
	 *            标识 1新增 2修改
	 */
	private void paramVerifi(SysRoleEntity entity, int mark) {
		if (mark == 2 && StrUtil.isBlank(entity.getId())) {
			throw new BusinessException("ID不能为空");
		}
		if (StrUtil.isBlank(entity.getRoleCode())) {
			throw new BusinessException("角色编码不能为空");
		}
		if (StrUtil.isBlank(entity.getRoleName())) {
			throw new BusinessException("角色名称不能为空");
		}
		/*
		 * if(StrUtil.isBlank(entity.getRoleType())){ throw new
		 * BusinessException("角色类型不能为空"); }
		 */
		/*
		 * if(StrUtil.isBlank(entity.getRoleSort().toString())){ throw new
		 * BusinessException("排序不能为空"); }
		 */
	}

	
	/**
	 * 查询当前角色的全部权限
	 */
	@RequestMapping("/v1/rolepermission")
	@RequiresPermissions("sys:role:list")
	public Result finRolePermission(String roleId){
		List<SysMenuEntity> menuList = sysMenuService.finRolePermission(roleId);
		return Result.ok().putList(menuList);
	}
	
	/**
	 * 授权
	 */
	@PostMapping("/v1/permission")
	@RequiresPermissions("sys:role:save")
	public Result permission(@RequestBody String param) {
		JSONObject obj = JSONObject.parseObject(param);
		String roleId = obj.getString("roleId");
		if(StrUtil.isBlank(roleId)){
			return Result.error("角色ID不能为空");
		}
		List<String> menuList = JSONArray.parseArray(obj.getString("menuArray"), String.class);
		if(CollectionUtil.isEmpty(menuList)){
			return Result.error("资源ID不能为空");
		}
		sysRoleMenuService.saveOrUpdate(roleId, menuList);
		return Result.ok();
	}
	@SysLog("用户分配角色")
	@RequestMapping("/v1/saveOrUpdateUserRole")
	@RequiresPermissions("sys:role:save")
	public Result saveOrUpdateUserRole(@RequestBody String json){
		JSONObject obj = JSONObject.parseObject(json);
		String roleId = (String)obj.get("roleId");
		if(StrUtil.isBlank(roleId)){
			return Result.error("角色ID不能为空");
		}
		List<String> companyIds = JSONArray.parseArray(obj.getString("companyIds"), String.class);
		if(CollectionUtil.isEmpty(companyIds)){
			return Result.error("公司ID不能为空");
		}
		List<String> userIds = JSONArray.parseArray(obj.getString("userIds"), String.class);
		if(CollectionUtil.isEmpty(userIds)){
			return Result.error("用户ID不能为空");
		}
		EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
		wrapper.eq("role_id", roleId);
		wrapper.in("company_id", companyIds);
		wrapper.in("user_id", userIds);
		if(sysUserRoleService.selectCount(wrapper) > 0){
			if(CollectionUtil.isEmpty(userIds)){
				return Result.error("已有用户分配角色请检查");
			}
		}
		sysUserRoleService.saveOrUpdateUserRoleByRole(new ArrayList<String>(),userIds, roleId, companyIds);
		return Result.ok();
	}
	

	@RequestMapping("/v1/getRoleList")
	public Result getRoleList() {
		EntityWrapper<SysRoleEntity> ew = new EntityWrapper<SysRoleEntity>();
		ew.eq("corp_code", getUser().getCorpCode());
		ew.eq("valid", "0");
		List<SysRoleEntity> list = sysRoleService.selectList(ew);
		companySetRoleList(list);
		return Result.ok().put(list);
	}
	
	
	
	private void companySetRoleList(List<SysRoleEntity> list) {
		List<RoleCompanyForm> formList = new ArrayList<RoleCompanyForm>();
		formList = sysRoleService.selectRoleCompanyList();
		if(formList != null && formList.size() > 0) {
			for(SysRoleEntity ent: list) {
				for(RoleCompanyForm f:formList) {				
					if(f.getRoleId().equals(ent.getId())){
						List<RoleCompanyForm> companyList = ent.getCompanyList();
						if(CollectionUtil.isEmpty(companyList)) {
							companyList = new ArrayList<RoleCompanyForm>();
						}
						companyList.add(f);
						ent.setCompanyList(companyList);
					}
				}				
			}
		}
	}
}
