/**
 * 
 */
package com.build.cloud.modules.bs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.dao.BsTeamDao;
import com.build.cloud.modules.bs.dao.BsWorkerDao;
import com.build.cloud.modules.bs.entity.BsTeamEntity;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;
import com.build.cloud.modules.bs.service.IBsTeamService;

/**
 * @className BsTeamServiceImpl
 * @descripion 班组档案
 * @author huangchao
 * @date 2018年4月12日下午7:35:15
 */
@Service("bsTeamService")
public class BsTeamServiceImpl extends ServiceImpl<BsTeamDao, BsTeamEntity> implements IBsTeamService {

	@Autowired
	private BsWorkerDao bsWorkerDao;
	
	/**
	 * @param teamName 班组名称
	 * @param teamCode 班组编码
	 */
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String teamName = MapUtil.getStr(params,"teamName");
		String teamCode = MapUtil.getStr(params,"teamCode");
		String teamLeader = MapUtil.getStr(params,"teamLeader");
		String teamType = MapUtil.getStr(params,"teamType");
		String companyId = MapUtil.getStr(params,"companyId");
		String phone = MapUtil.getStr(params,"phone");
		String status = MapUtil.getStr(params,"status");
		//String keywords = MapUtil.getStr(params,"keywords");
		EntityWrapper<BsTeamEntity> wrApper = new EntityWrapper<BsTeamEntity>();
		wrApper.like(StrUtil.isNotBlank(teamName),"team_name", teamName);
		if(!StrUtil.isBlank(teamCode)){
			wrApper.in(StrUtil.isNotBlank(teamCode),"team_code", teamCode);
		}
		if(!StrUtil.isBlank(teamType)){
			wrApper.in(StrUtil.isNotBlank(teamType),"team_type", teamType);
		}
		wrApper.like(StrUtil.isNotBlank(teamLeader),"team_leader", teamLeader);
		wrApper.eq(StrUtil.isNotBlank(phone),"phone", phone);
		wrApper.eq(StrUtil.isNotBlank(companyId),"company_id", companyId);
		wrApper.eq(StrUtil.isNotBlank(status),"status", status);
//		wrApper.eq("status","0");
		Page<BsTeamEntity> page =
			this.selectPage(new Query<BsTeamEntity>(params).getPage(),wrApper);
		if(!CollectionUtil.isEmpty(page.getRecords())) {
			for (BsTeamEntity bsTeamEntity : page.getRecords()) {
				if(bsTeamEntity != null && !StrUtil.isBlank(bsTeamEntity.getTeamLeaderId())) {
					BsWorkerEntity workerEntity = bsWorkerDao.selectById(bsTeamEntity.getTeamLeaderId());
					if(workerEntity != null) {
						bsTeamEntity.setTeamLeader(workerEntity.getName());
					}
					
				}
				
			}
		}
		return new PageUtils(page);
	}
	
	public PageUtils queryTeam(Map<String, Object> params){
		Page<BsTeamEntity> page = new Query<BsTeamEntity>(params).getPage();
		page.setRecords(baseMapper.selectTeamByProjectId(page, params));
		return new PageUtils(page);
	}
}
