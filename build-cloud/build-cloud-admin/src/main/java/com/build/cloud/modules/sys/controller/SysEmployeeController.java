package com.build.cloud.modules.sys.controller;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;
import com.build.cloud.modules.bs.service.IBsWorkerService;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.SysEmployeeTypeEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.SysUserRoleEntity;
import com.build.cloud.modules.sys.entity.vo.EmployeeVo;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.build.cloud.modules.sys.service.ISysEmployeeTypeService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.build.cloud.modules.sys.service.ISysUserService;

import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysEmployeeController
 * @Description: 员工信息表
 * @author: liutao
 * @date: 2018年3月31日 上午9:51:34
 */
@RestController
@RequestMapping("/sys/employee")
public class SysEmployeeController extends AbstractController {
	
	@Autowired
	private ISysUserService sysUserService;
    @Autowired
    private ISysEmployeeService sysEmployeeService; 
    @Autowired
    private ISysEmployeeTypeService sysEmployeeTypeService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
	@Autowired
	private IBsWorkerService workerService;
    /**
     * 列表
     */
    @RequestMapping("/v1/list")
    @RequiresPermissions("sys:employee:list")
    public Result list(@RequestParam Map<String, Object> params){
    	params.put("corpCode", getCorpCode());//获取当前登陆人员归属集团CODE
    	params.put("companyId", getCurrentCompanyId());
        PageUtils page = sysEmployeeService.queryPage(params);
        return Result.ok().put(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/v1/info")
    @RequiresPermissions("sys:employee:info")
    public Result info(@RequestBody String id){
		SysEmployeeEntity sysEmployee = sysEmployeeService.selectById(id);
		SysUserEntity userEnt = sysUserService.selectById(sysEmployee.getUserId());
		EmployeeVo vo = new EmployeeVo();
		setEntVo(vo, sysEmployee, userEnt);
		if(StringUtil.isNotBlank(sysEmployee.getEmpTypeId())) {
			SysEmployeeTypeEntity typeEnt = sysEmployeeTypeService.selectById(sysEmployee.getEmpTypeId());
			vo.setEmpTypeName(typeEnt.getEmpTypeName());
		}
        return Result.ok().putEntity(vo);
    }

    /**
     * 保存
     */ 
    @RequestMapping("/v1/save")
    @RequiresPermissions("sys:employee:save")
    public Result save(@RequestBody EmployeeVo vo){
    	
    	if(!StringUtil.is11Phone(vo.getContactInfo())) {
    		return Result.error("请输入正确的电话号码");
		}
		
		if(!StringUtil.is18Idcard(vo.getIdNum())) {
			return Result.error("请输入正确的身份证号码");
		}
		int employeeCount = sysEmployeeService.selectCount(new EntityWrapper<SysEmployeeEntity>().eq("id_num", vo.getIdNum()));
		int workCount = workerService.selectCount(new EntityWrapper<BsWorkerEntity>().eq("idcard", vo.getIdNum()));
		if(employeeCount > 0 || workCount > 0) {
			return Result.error("该身份证号码已存在！");
		}
		EntityWrapper<SysUserEntity> phoneEw = new EntityWrapper<>();
		phoneEw.eq("mobile", vo.getContactInfo());
    	int phoneSeiz = sysUserService.selectCount(phoneEw);
    	if(phoneSeiz > 0) {
    		return Result.error("手机号码重复，用户已注册！");
    	}  
		
    	EntityWrapper<SysUserEntity> ew = new EntityWrapper<>();
    	ew.eq("login_name", vo.getLoginName());
    	int seiz = sysUserService.selectCount(ew);
    	if(seiz > 0) {
    		return Result.error("用户登录账号重复请重新设置");
    	}    	
    	vo.setCorpCode(getUser().getCorpCode());
    	vo.setCorpName(getUser().getCorpName());
		sysEmployeeService.insertEmployeeVo(vo);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/v1/update")
    @RequiresPermissions("sys:employee:update")
    public Result update(@RequestBody EmployeeVo vo){
    	if(!StringUtil.is11Phone(vo.getContactInfo())) {
    		return Result.error("请输入正确的电话号码");
		}
		
		if(!StringUtil.is18Idcard(vo.getIdNum())) {
			return Result.error("请输入正确的身份证号码");
		}
    	
    	EntityWrapper<SysUserEntity> ew = new EntityWrapper<>();
    	ew.eq("login_name", vo.getLoginName());
    	ew.ne("id", vo.getUserId());
    	int seiz = sysUserService.selectCount(ew);
    	if(seiz > 0) {
    		return Result.error("用户登录账号重复请重新设置");
    	}
    	vo.setCorpCode(getUser().getCorpCode());
    	vo.setCorpName(getUser().getCorpName());
    	boolean ret = sysEmployeeService.updateEmployeeVo(vo);
    	if(!ret) {
    		 return Result.error("错误数据找不到修改账户信息");
    	}
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/v1/delete")
    @RequiresPermissions("sys:employee:delete")
    public Result delete(@RequestBody String id){
    	JSONObject obj = JSON.parseObject(id);
    	id = obj.getString("id");
    	SysEmployeeEntity ent = sysEmployeeService.selectById(id);
    	
    	EntityWrapper<SysUserRoleEntity> ew = new EntityWrapper<SysUserRoleEntity>();
		ew.eq("user_id", ent.getUserId());
		int count = sysUserRoleService.selectCount(ew);
		if(count > 0 ) {
			return Result.error("人员已分配角色，无法删除！");
		}
		if (StrUtil.equals(ent.getUserId(), getUserId())) {
			return Result.error("当前用户不能删除");
		}
    	
		sysEmployeeService.deleteUserInfo(ent);
		
		
        return Result.ok();
    }
    
    private void setEntVo(EmployeeVo vo,SysEmployeeEntity empEnt,SysUserEntity useEnt) {
    	
    	vo.setId(empEnt.getId());
    	vo.setDeptId(empEnt.getDeptId());
    	vo.setDeptName(empEnt.getDeptName());
    	vo.setCompanyId(empEnt.getCompanyId());
    	vo.setCompanyName(empEnt.getCompanyName());
    	vo.setEmpName(empEnt.getEmpName());
    	vo.setEmpNameEn(empEnt.getEmpNameEn());
    	vo.setRemark(empEnt.getRemark());
    	vo.setCorpCode(empEnt.getCorpCode());
    	vo.setCorpName(empEnt.getCorpName());    	
    	vo.setArchiveMark(empEnt.getArchiveMark());
    	vo.setEntryDate(empEnt.getEntryDate());
    	vo.setIdNum(empEnt.getIdNum());
    	vo.setContactInfo(empEnt.getContactInfo());
    	vo.setLiveAddress(empEnt.getLiveAddress());
    	vo.setSex(empEnt.getSex());
    	vo.setBirthday(empEnt.getBirthday());
    	vo.setEmergencyContact(empEnt.getEmergencyContact());
    	vo.setEmpTypeId(empEnt.getEmpTypeId());
    	vo.setAge(empEnt.getAge());    	
    	
    	vo.setUserId(useEnt.getId());
    	vo.setStatus(useEnt.getStatus());
    	vo.setDepartureDate(useEnt.getDepartureDate());
    	vo.setLoginName(useEnt.getLoginName());
    	vo.setEmail(useEnt.getEmail());
    	vo.setMgrType(useEnt.getMgrType());
    }

}