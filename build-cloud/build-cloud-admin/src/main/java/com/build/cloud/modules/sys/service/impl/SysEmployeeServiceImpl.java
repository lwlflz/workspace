package com.build.cloud.modules.sys.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.sys.dao.SysEmployeeDao;
import com.build.cloud.modules.sys.entity.SysEmployeeEntity;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.entity.vo.EmployeeVo;
import com.build.cloud.modules.sys.service.ISysEmployeeService;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
@Service("sysEmployeeService")
public class SysEmployeeServiceImpl extends ServiceImpl<SysEmployeeDao, SysEmployeeEntity>
		implements ISysEmployeeService {
	
	@Autowired
	ISysUserService sysUserService;	
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<EmployeeVo> query = new Query<EmployeeVo>(params);
		Page<EmployeeVo> page = query.getPage();
		List<EmployeeVo> records = baseMapper.selectPageByVo(page, query);
		page.setRecords(records);
		return new PageUtils(page);
	}
	
	/**
	 * 新增用户
	 */
	@Transactional
	public void insertEmployeeVo(EmployeeVo vo) {
		vo.setStatus("0");
		SysEmployeeEntity empEnt = new SysEmployeeEntity();
		SysUserEntity useEnt = new SysUserEntity();
		setVoEnt(vo, empEnt, useEnt);
		// sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		useEnt.setSalt(salt);
		/**如果前端密码参数为空，则取身份证后6位做为默认密码，如果密码不为空，则不做处理**/
		if(com.sunsine.common.util.StringUtil.isNull(useEnt.getPassword())) {
			useEnt.setPassword(ShiroUtils.sha256(StringUtil.getIdcard6(vo.getIdNum()), useEnt.getSalt()));
		}else {
			useEnt.setPassword(ShiroUtils.sha256(useEnt.getPassword(), useEnt.getSalt()));	//ShiroUtils.sha256(useEnt.getPassword(), useEnt.getSalt())
		}
		/************************************************************/
		sysUserService.insert(useEnt);
		empEnt.setUserId(useEnt.getId());
		baseMapper.insert(empEnt);
	}
	
	/**
	 * 修改用户
	 */
	@Transactional
	public boolean updateEmployeeVo(EmployeeVo vo) {
		SysEmployeeEntity empEnt = baseMapper.selectById(vo.getId());
		SysUserEntity useEnt = sysUserService.selectById(vo.getUserId());		
		
		if(empEnt ==null|| useEnt == null) {
			return false;
		}		
		String pwd = useEnt.getPassword();
		setVoEnt(vo, empEnt, useEnt);
		
		if (StrUtil.isNotBlank(vo.getPassword())) {
			String salt = RandomStringUtils.randomAlphanumeric(20);
			useEnt.setSalt(salt);
			useEnt.setPassword(ShiroUtils.sha256(vo.getPassword(), salt));//ShiroUtils.sha256(useEnt.getPassword(), useEnt.getSalt())
		}else {
			useEnt.setPassword(pwd);
		}
		
		sysUserService.updateAllColumnById(useEnt);
		baseMapper.updateAllColumnById(empEnt);
		return true;
	}
	
	@Transactional
	public void deleteUserInfo(SysEmployeeEntity empEnt) {
		SysUserEntity useEnt = sysUserService.selectById(empEnt.getUserId());		
		if(useEnt != null) {
			sysUserService.deleteById(useEnt.getId());
		}
		baseMapper.deleteById(empEnt.getId());
	}
	
    
    private void setVoEnt(EmployeeVo vo,SysEmployeeEntity empEnt,SysUserEntity useEnt) {
    	BeanUtil.copyProperties(vo, empEnt);
    	BeanUtil.copyProperties(vo, useEnt, "status");
    	empEnt.setId(vo.getId());
    	useEnt.setUserName(vo.getEmpName());
    	//empEnt.setUserId(vo.getUserId());
    	
//    	empEnt.setDeptId(vo.getDeptId());
//    	empEnt.setDeptName(vo.getDeptName());
//    	empEnt.setCompanyId(vo.getCompanyId());
//    	empEnt.setCompanyName(vo.getCompanyName());
//    	empEnt.setEmpName(vo.getEmpName());
//    	empEnt.setEmpNameEn(vo.getEmpNameEn());
//    	empEnt.setRemark(vo.getRemark());
//    	empEnt.setCorpCode(vo.getCorpCode());
//    	empEnt.setCorpName(vo.getCorpName());    	
//    	empEnt.setArchiveMark(vo.getArchiveMark());
//    	empEnt.setEntryDate(vo.getEntryDate());
//    	empEnt.setIdNum(vo.getIdNum());
//    	empEnt.setContactInfo(vo.getContactInfo());
//    	empEnt.setLiveAddress(vo.getLiveAddress());
//    	empEnt.setSex(vo.getSex());
//    	empEnt.setBirthday(vo.getBirthday());
//    	empEnt.setEmergencyContact(vo.getEmergencyContact());    	
//    	empEnt.setEmpTypeId(vo.getEmpTypeId());
//    	empEnt.setAge(vo.getAge());
    	
    	useEnt.setId(vo.getUserId());
    	useEnt.setMobile(vo.getContactInfo());
//    	useEnt.setCorpCode(vo.getCorpCode());
//    	useEnt.setCorpName(vo.getCorpName());
//    	useEnt.setStatus(vo.getStatus());
//    	useEnt.setUserName(vo.getEmpName());
//    	useEnt.setCompanyId(vo.getCompanyId());
//    	useEnt.setDepartureDate(vo.getDepartureDate());
//    	useEnt.setLoginName(vo.getLoginName());
//    	useEnt.setPassword(vo.getPassword());
//    	useEnt.setEmail(vo.getEmail());
//    	useEnt.setMgrType(vo.getMgrType());
    }
    
}
