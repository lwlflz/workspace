package com.build.cloud.modules.sys.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.annotation.DataFilter;
import com.build.cloud.modules.sys.dao.SysDeptDao;
import com.build.cloud.modules.sys.entity.SysCompanyEntity;
import com.build.cloud.modules.sys.entity.SysDeptEntity;
import com.build.cloud.modules.sys.service.ISysCompanyService;
import com.build.cloud.modules.sys.service.ISysDeptService;
import com.google.common.collect.Lists;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.PinyinUtil;
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements ISysDeptService {
	@Autowired
	private ISysCompanyService sysCompanyService;
	
	@Override
	@DataFilter(subDept = true, user = false)
	public List<SysDeptEntity> queryList(Map<String, Object> params) {
		EntityWrapper<SysDeptEntity> ew = new EntityWrapper<SysDeptEntity>();
		ew.eq("corp_code", MapUtil.getStr(params, "corpCode"));
		ew.eq("company_id", MapUtil.getStr(params, "companyId"));
		List<SysDeptEntity> deptList = this.selectList(ew);
		for (SysDeptEntity sysDeptEntity : deptList) {
			SysDeptEntity parentDeptEntity = this.selectById(sysDeptEntity.getParentId());
			if (parentDeptEntity != null) {
				sysDeptEntity.setParentName(parentDeptEntity.getDeptName());
			}
			SysCompanyEntity companyEntity = sysCompanyService.selectById(sysDeptEntity.getCompanyId());
			if(companyEntity != null){
				sysDeptEntity.setCompanyName(companyEntity.getCompanyName());
			}
		}
		return deptList;
	}
	
	@Override
//	@DataFilter(subDept = true, user = false)
	public List<SysDeptEntity> getDeptTreeByCompanyId(String companyId){
		List<SysDeptEntity> deptList = baseMapper.getDeptTreeByCompanyId(companyId);

		for(SysDeptEntity sysDeptEntity : deptList){
			SysDeptEntity parentDeptEntity =  this.selectById(sysDeptEntity.getParentId());
			if(parentDeptEntity != null){
				sysDeptEntity.setParentName(parentDeptEntity.getDeptName());
			}
		}
		return deptList;
	}
	
	@Override
	public List<String> queryDetpIdList(String parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}
	@Override
	public List<String> getSubDeptIdList(String deptId) {
		// 部门及子部门ID列表
		List<String> deptIdList = Lists.newArrayList();
		// 获取子部门ID
		List<String> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);
		return deptIdList;
	}
	/**
	 * 递归
	 */
	private void getDeptTreeList(List<String> subIdList, List<String> deptIdList) {
		for (String deptId : subIdList) {
			List<String> list = queryDetpIdList(deptId);
			if (CollectionUtil.isNotEmpty(list)) {
				getDeptTreeList(list, deptIdList);
			}
			deptIdList.add(deptId);
		}
	}
	@Override
	public List<Map<String, Object>> getDeptByDeptName(String deptName) {
		EntityWrapper<SysDeptEntity> ew = new EntityWrapper<SysDeptEntity>();
		deptName = PinyinUtil.getAllFirstLetter(deptName);// 中文转换英文
		ew.like("pinyin_code", deptName).and().eq("status", "0");
		List<SysDeptEntity> deptList = this.selectList(ew);
		List<Map<String, Object>> list = Lists.newArrayList();
		for (SysDeptEntity entity : deptList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", entity.getId());
			map.put("name", entity.getDeptName());
			map.put("deptCode", entity.getDeptCode());
			list.add(map);
		}
		return list;
	}
	
}
