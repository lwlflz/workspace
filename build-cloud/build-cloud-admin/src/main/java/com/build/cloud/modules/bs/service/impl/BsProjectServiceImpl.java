/**
 * 
 */
package com.build.cloud.modules.bs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsProjectDao;
import com.build.cloud.modules.bs.dao.OpPublicProjectDao;
import com.build.cloud.modules.bs.entity.BsProjectEntity;
import com.build.cloud.modules.bs.entity.OpPublicProjectEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.common.service.ICommonService;
import com.build.cloud.modules.sys.dao.SysCompanyDao;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;

/**
 * @className BsProjectFileServiceImpl
 * @descripion 项目档案--实现层
 * @author huangchao
 * @date 2018年4月11日下午3:13:41
 */
@Service("bsProjectFileService")
public class BsProjectServiceImpl extends ServiceImpl<BsProjectDao, BsProjectEntity> implements IBsProjectService {
	
	@Autowired
	private SysCompanyDao sysCompanyDao;
	
	@Autowired
	private OpPublicProjectDao opPublicProjectDao;
	
	@Autowired
	private ICommonService commonService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		List<Map<String, Object>> records = baseMapper.findProjectListByObject(page,query);
		List<Map<String, Object>> resutlMap = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : records) {
			Map<String, String> comMap = commonService.getCompanyProType(params
					.get("companyId").toString(), map.get("id")
					.toString());
			String companyRoleType = comMap.get(Constant.COMPANY_ROLE_TYPE);
			map.put("companyRoleType", companyRoleType);
			resutlMap.add(map);
		}
		page.setRecords(resutlMap);
		return new PageUtils(page);
	}

	@Override
	public List<BsProjectEntity> getProjectListByCompanyId(String companyId) {
		return baseMapper.getProjectListByCompanyId(companyId);
	}

	@Override
	public List<Map<String, Object>> getProById(Map<String, Object> params) {
		return baseMapper.findByProId(params);
	}

	@Override
	public int getPlanCiteProject(String projectId) {
		return baseMapper.getPlanCiteProject(projectId);
	}
	@Override
	public List<OpPublicProjectEntity> queryList(Map<String, Object> params) {
		EntityWrapper<OpPublicProjectEntity> wrapper = new EntityWrapper<OpPublicProjectEntity>();
		String opPublicProjectCode = MapUtil.getStr(params, "opPublicProjectCode");
		String opPublicProjectName = MapUtil.getStr(params, "opPublicProjectName");
		wrapper.like(StrUtil.isNotBlank(opPublicProjectCode),"op_project_code", opPublicProjectCode);
		wrapper.like(StrUtil.isNotBlank(opPublicProjectName),"op_project_name", opPublicProjectName);
		return opPublicProjectDao.selectList(wrapper);
	}

	/**
	 * 生产计划相关--根据登录用户获取项目id(总包登录则返回总包项目id，分包登录则先查公共项目，再找到总包项目id返回)
	 * @param projectId
	 * @return
	 */
	@Override
	public Map<String,String> quryProProjectId(String projectId) {
		String companyId = ShiroUtils.getUserEntity().getCompanyId();
		Map<String,String> map = commonService.getCompanyProType(companyId, projectId);
		String roleType = map.get(Constant.COMPANY_ROLE_TYPE);
		if(Objects.equal(roleType, "2")){//总包
			map.put("projectId", projectId);
		}else if(Objects.equal(roleType, "3")){//分包
			String proId = map.get(Constant.PRO_ID);
			String beneralcontractor = map.get(Constant.CompanyProRole.BENERALCONTRACTOR.getName());
			
			BsProjectEntity ew = new BsProjectEntity();
			ew.setCompanyId(beneralcontractor);
			ew.setProId(proId);
			BsProjectEntity res = baseMapper.selectOne(ew);
			if(res!=null){
				map.put("projectId", res.getId());
			}
		}
		return map;
	}
	
}
