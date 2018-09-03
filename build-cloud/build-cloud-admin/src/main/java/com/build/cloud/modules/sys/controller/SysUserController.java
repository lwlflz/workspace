package com.build.cloud.modules.sys.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.constant.Constant.AdminType;
import com.build.cloud.common.constant.Constant.UserType;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.common.validator.Assert;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.common.validator.group.AddGroup;
import com.build.cloud.common.validator.group.UpdateGroup;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.bean.EmployeeBean;
import com.build.cloud.modules.sys.bean.MenuBean;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.SysEmployeeTypeEntity;
import com.build.cloud.modules.sys.entity.SysMenuEntity;
import com.build.cloud.modules.sys.entity.SysRoleEntity;
import com.build.cloud.modules.sys.entity.SysRoleMenuEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;
import com.build.cloud.modules.sys.form.CompanyUserForm;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.build.cloud.modules.sys.service.ISysEmployeeTypeService;
import com.build.cloud.modules.sys.service.ISysMenuService;
import com.build.cloud.modules.sys.service.ISysRoleMenuService;
import com.build.cloud.modules.sys.service.ISysRoleService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysUserController
 * @Description: 系统用户
 * @author: liutao
 * @date: 2018年3月16日 下午2:49:35
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysMenuService sysMenuService;
	@Autowired
    private ISysEmployeeTypeService sysEmployeeTypeService;
	@Autowired
    private ISysRoleService sysRoleService;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	/**
	 * 所有用户列表
	 */
	@GetMapping("/v1/findRoleByUserId")
//	@RequiresPermissions("sys:user:list")
	public Result findRoleByUserId(String userId) {
		if(StrUtil.isBlank(userId)){
			return Result.error("用户ID不能为空");
		}
		List<SysRoleEntity> list = sysRoleService.findRoleByUserId(userId);
		return Result.ok().putList(list);
	}
	
	/**
	 * 所有用户列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:user:list")
	public Result list(@RequestParam Map<String, Object> params) {
		params.put("corpCode", getUser().getCorpCode());//获取当前登陆用户归属集团CODE
		PageUtils page = sysUserService.queryPage(params);
		return Result.ok().put("result", page);
	}
	
	/**
	 * 用户所拥有的角色列表
	 */
	@GetMapping("/v1/userRoleList")
	public Result userRoleList(@RequestParam Map<String, Object> params) {
		params.put("userId", getUserId());
		PageUtils page = sysUserService.queryUserRolePage(params);
		return Result.ok().put("result", page);
	}
	
	
	/**
	 * 获取用户公司列表
	 */
	@GetMapping("/v1/userCompanyList")
	public Result userCompanyList() {		
		Map<String, Object> params = Maps.newHashMap();
		params.put("corpCode", getUser().getCorpCode());
		params.put("userId", getUserId());
		params.put("mgrType", getUser().getMgrType());
		
		List<Map<String, Object>> list = sysUserService.querCompanyByUserId(params);
		return Result.ok().put(list);
	}
	
	/**
	 * 传入登陆选择公司保存进redis
	 */
//	@SysLog("保存选择公司ID")
	@RequestMapping("/v1/saveCompanyIdRedis")
	public Result saveCompanyIdRedis(@RequestBody String companyId) {
		JSONObject obj = JSONObject.parseObject(companyId);
		companyId = (String)obj.get("companyId");
		redisUtils.delete(RedisKeys.getDefCom(getUserId()));//先从redis删除之前保存的公司ID
		redisUtils.set(RedisKeys.getDefCom(getUserId()), companyId, RedisUtils.DEFAULT_EXPIRE);//把选择公司ID以当前登陆用户ID作为键 存入redis
		return Result.ok();
	}
	
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/v1/password")
	public Result password(String password, String newPassword) {
		Assert.isBlank(newPassword, "新密码不为能空");
		// 原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		// 新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
		// 更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if (!flag) {
			return Result.error("原密码不正确");
		}
		return Result.ok();
	}
	/**
	 * 用户信息
	 */
	@GetMapping("/v1/info")
	public Result info() {
		if(StrUtil.isBlank(getCurrentCompanyId())) {
			return Result.error(401, "请重新登录");
		}
		String userId = getUserId();
		
		SysUserEntity user = sysUserService.selectById(userId);
		
		String userType = user.getUserType();
		EmployeeBean bean = new EmployeeBean();
		if(StrUtil.equals(userType, UserType.EMPLOYEE.getValue())) {
			EntityWrapper<SysEmployeeEntity> wrapper = new EntityWrapper<SysEmployeeEntity>();
			wrapper.eq("user_id", userId);
			SysEmployeeEntity empEntity = sysEmployeeService.selectOne(wrapper);
//			user.setEmpEntity(empEntity);
			setEntVo(bean, empEntity, user);
			if(empEntity != null && StrUtil.isNotBlank(empEntity.getEmpTypeId())) {
				SysEmployeeTypeEntity typeEnt = sysEmployeeTypeService.selectById(empEntity.getEmpTypeId());
				if(typeEnt != null) {
					bean.setEmpTypeName(typeEnt.getEmpTypeName());
				}
			}
		}		
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(userId);
		
		List<MenuBean> menus = Lists.newArrayList();
		if(CollectionUtil.isNotEmpty(menuList) ) {
			for (SysMenuEntity sysMenuEntity : menuList) {
				MenuBean mBean = new MenuBean();
				BeanUtil.copyProperties(sysMenuEntity, mBean);
				menus.add(mBean);
			}
		}
		
//		bean.setMenuList(menuList);
		SysUserEntity entity = getUser();
		EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
		wrapper.eq("user_id", getUserId());
		wrapper.eq("company_id", getCurrentCompanyId());
		List<SysUserRoleEntity> list = sysUserRoleService.selectList(wrapper);
		//List<List<SysMenuEntity>> userRoleMenuList = new ArrayList<List<SysMenuEntity>>();
		List<SysMenuEntity> userMenuList = new ArrayList<SysMenuEntity>();
		for (SysUserRoleEntity sysUserRoleEntity : list) {
			EntityWrapper<SysRoleMenuEntity> roleMenuWrapper = new EntityWrapper<SysRoleMenuEntity>();
			roleMenuWrapper.eq("role_id", sysUserRoleEntity.getRoleId());
			List<SysRoleMenuEntity> roleMenuList = sysRoleMenuService.selectList(roleMenuWrapper);
			for (SysRoleMenuEntity sysRoleMenuEntity : roleMenuList) {
				EntityWrapper<SysMenuEntity> menuWrapper = new EntityWrapper<SysMenuEntity>();
				menuWrapper.eq("id", sysRoleMenuEntity.getMenuId());
				SysMenuEntity mEntity = sysMenuService.selectOne(menuWrapper);
				if (mEntity != null) {
					userMenuList.add(mEntity);
				}
			}
			entity.setMenuList(userMenuList);
			//entity.setMenuList(sys.selectList(menuWrapper));
		}
		
		//如果是集团管理员,则设置集团关联的套餐权限对应的菜单
		//原来代码非我所写，写在这里是为了只改集团管理员的菜单获取方式，其他的非集团管理员走原来逻辑
		if (Objects.equal(user.getMgrType(), "2")) {//2-集团管理员
			entity.setMenuList(menuList);
		}
		
		Map<String, Object> result = Maps.newLinkedHashMap();
		result.put("entity", bean);
		result.put("list", menus);
		//getUser().setMenuList(userRoleMenuList);
		result.put("user", entity);
		return Result.ok().put(result);
	}
	/**
	 * 用户信息
	 */
	@GetMapping("/v1/infoApp")
	public Result infoApp() {
		SysUserEntity entity = getUser();
		Map<String, Object> result = Maps.newLinkedHashMap();
		result.put("user", entity);
		return Result.ok().put(result);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/v1/save")
	@RequiresPermissions("sys:user:save")
	public Result save(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, AddGroup.class);
		user.setCorpCode(getUser().getCorpCode());
		user.setCorpName(getUser().getCorpName());
		sysUserService.save(user);
		return Result.ok();
	}
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/v1/update")
	@RequiresPermissions("sys:user:update")
	public Result update(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.update(user);
		redisUtils.delete(RedisKeys.getUserKey(user.getId()));
		return Result.ok();
	}
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/v1/delete")
	@RequiresPermissions("sys:user:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		if (StrUtil.equals(id, "1")) {
			return Result.error("系统管理员不能删除");
		}
		SysUserEntity user = sysUserService.selectById(id);
		if (StrUtil.equals(user.getMgrType(), AdminType.SUPERAMIN.getValue())) {
			return Result.error("超级管理员不能删除");
		}
		if (StrUtil.equals(id, getUserId())) {
			return Result.error("当前用户不能删除");
		}
		sysUserService.deleteById(id);
		redisUtils.delete(RedisKeys.getUserKey(id));
		return Result.ok("用户删除成功");
	}
	
	/**
	 * 用户分配角色、公司
	 */
	@SysLog("用户分配角色")
	@RequestMapping("/v1/saveOrUpdateUserRole")
	@RequiresPermissions("sys:user:save")
	public Result saveOrUpdateUserRole(@RequestBody String json){
		JSONObject obj = JSONObject.parseObject(json);
		List<String> laborArray = JSONArray.parseArray(obj.getString("laborArray"), String.class);
		String roleId = (String)obj.get("roleId");
		if(StrUtil.isBlank(roleId)){
			return Result.error("角色ID不能为空");
		}
		List<String> companyIds = JSONArray.parseArray(obj.getString("companyIds"), String.class);
		if(CollectionUtil.isEmpty(companyIds)){
			return Result.error("公司ID不能为空");
		}
		List<String> empArray = JSONArray.parseArray(obj.getString("empArray"), String.class);
		if(CollectionUtil.isEmpty(empArray)&&CollectionUtil.isEmpty(laborArray)){
			return Result.error("人员ID不能为空");
		}
		EntityWrapper<SysUserRoleEntity> wrapper = new EntityWrapper<SysUserRoleEntity>();
		wrapper.eq("role_id", roleId);
		wrapper.in("company_id", companyIds);
		if(!CollectionUtil.isEmpty(empArray) && CollectionUtil.isEmpty(laborArray)){
			wrapper.in("user_id", empArray);
		}else if(CollectionUtil.isEmpty(empArray) && !CollectionUtil.isEmpty(laborArray)){
			wrapper.in("user_id", laborArray);
		}else{
			wrapper.in("user_id", empArray).or().in("user_id", laborArray);
		}
		if(sysUserRoleService.selectCount(wrapper) > 0){
			return Result.error("已有用户分配角色请检查");
		}
		sysUserRoleService.saveOrUpdateUserRoleByRole(laborArray,empArray, roleId, companyIds);
		return Result.ok();
	}
	
	/**
	 * 用户分配角色、公司
	 */
	@SuppressWarnings("unchecked")
	@SysLog("用户分配角色")
	@RequestMapping("/v1/saveOrUpdateUserRoleByUser")
	@RequiresPermissions("sys:user:save")
	public Result saveOrUpdateUserRoleByUser(@RequestBody String json){
		JSONObject obj = JSONObject.parseObject(json);
		String userId = obj.getString("userId");
		List<Map<String, Object>> listMap = JSON.parseObject(obj.getString("roleList"), List.class);
		sysUserRoleService.saveOrUpdateUserRoleByUser(listMap, userId);
		return Result.ok();
	}
	
	/**
     * 获取人员档案人员列表
     */
    @RequestMapping("/v1/getEmpList")
    public Result getEmpList(String companyId,String userName){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("companyId", companyId);
    	params.put("userName", userName);
		List<CompanyUserForm> empList = sysUserService.selectEmpUserList(params);
    	if(empList == null||empList.size() ==0) {
    		empList = new ArrayList<CompanyUserForm>();
    	}
        return Result.ok().put(empList);
    }
    
    /**
     * 获取劳务档案人员列表
     */
    @RequestMapping("/v1/getLaborList")
    public Result getLaborList(String userName){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("userName", userName);
    	params.put("companyId", getCurrentCompanyId());
		List<CompanyUserForm> laborList = sysUserService.selectLaborUserList(params);
		if(laborList == null||laborList.size() ==0) {
			laborList = new ArrayList<CompanyUserForm>();
		}
        return Result.ok().put(laborList);
    }
	
	
	private void setEntVo(EmployeeBean bean,SysEmployeeEntity empEnt,SysUserEntity useEnt) {
    	BeanUtil.copyProperties(empEnt, bean);
    	bean.setUserId(useEnt.getId());
    	bean.setStatus(useEnt.getStatus());
    	bean.setDepartureDate(useEnt.getDepartureDate());
    	bean.setLoginName(useEnt.getLoginName());
    	bean.setEmail(useEnt.getEmail());
    }
	
}
