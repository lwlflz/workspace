package com.build.cloud.modules.bs.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.exception.ServiceException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.modules.bs.bean.BsWorkerBean;
import com.build.cloud.modules.bs.dao.BsWorkerDao;
import com.build.cloud.modules.bs.entity.BsWorkerCertificateEntity;
import com.build.cloud.modules.bs.entity.BsWorkerContractEntity;
import com.build.cloud.modules.bs.entity.BsWorkerEntity;
import com.build.cloud.modules.bs.service.IBsWorkerCertificateService;
import com.build.cloud.modules.bs.service.IBsWorkerContractService;
import com.build.cloud.modules.bs.service.IBsWorkerService;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.build.cloud.modules.sys.util.DictUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;


@Service("bsWorkerService")
public class BsWorkerServiceImpl extends ServiceImpl<BsWorkerDao, BsWorkerEntity> implements IBsWorkerService {
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IBsWorkerCertificateService workerCertificateService;
	@Autowired
	private IBsWorkerContractService workerContractService;
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Query<Map<String, Object>> query = new Query<Map<String, Object>>(params);
		Page<Map<String, Object>> page = query.getPage();
		List<Map<String, Object>> records = baseMapper.selectPageByMap(page, query);
		for (Map<String, Object> map : records) {
			map.put("skillLevelName", DictUtils.getDictLabelByCode(map.get("skillLevel").toString(), "skill_level", ""));
			map.put("serverAddress", "http://192.168.10.192:9999/group1/");
		}
		page.setRecords(records);
		return new PageUtils(page);
	}
	@Transactional
	@Override
	public void save(BsWorkerEntity workerEntity, SysUserEntity userEntity) {
		List<BsWorkerContractEntity> workerContractList = workerEntity.getWorkerContractList();
		if(!CollectionUtil.isEmpty(workerContractList)) {
			workerEntity.setIsSignedContract("1");
		}
		sysUserService.insert(userEntity);
		workerEntity.setUserId(userEntity.getId());
		super.insert(workerEntity);
		List<BsWorkerCertificateEntity> workerCertificateList = workerEntity.getWorkerCertificateList();
		if(!CollectionUtil.isEmpty(workerContractList)) {
			for (BsWorkerContractEntity bsWorkerContractEntity : workerContractList) {
				String urlPath = "";
				String imgName = "";
				List<Map<String, Object>> attachmentListList = bsWorkerContractEntity.getAttachmentList();
		
				if(!CollectionUtil.isEmpty(attachmentListList)) {
					for (Map<String, Object> map : attachmentListList) {
						urlPath += map.get("url") + ",";
						imgName += map.get("name") + ",";
					}
				}
				if(!StrUtil.isBlank(urlPath)) {
					urlPath = urlPath.substring(0,urlPath.length()-1);
				}
				if(!StrUtil.isBlank(imgName)) {
					imgName = imgName.substring(0,imgName.length()-1);
				}
				bsWorkerContractEntity.setAttachmentUrl(urlPath);
				bsWorkerContractEntity.setAttachmentName(imgName);
				bsWorkerContractEntity.setWorkerId(workerEntity.getId());
				workerContractService.insert(bsWorkerContractEntity);
			}
		}
		if(!CollectionUtil.isEmpty(workerCertificateList)) {
			for (BsWorkerCertificateEntity bsWorkerCertificateEntity : workerCertificateList) {
				bsWorkerCertificateEntity.setWorkerId(workerEntity.getId());
				workerCertificateService.insert(bsWorkerCertificateEntity);
			}
		}
	}
	@Override
	public void update(BsWorkerEntity workerEntity, SysUserEntity userEntity) {
		List<BsWorkerContractEntity> workerContractList = workerEntity.getWorkerContractList();
		if(!CollectionUtil.isEmpty(workerContractList)) {
			workerEntity.setIsSignedContract("1");
		}else {
			workerEntity.setIsSignedContract("0");
		}
		sysUserService.update(userEntity);
		updateAllColumnById(workerEntity);
		List<BsWorkerCertificateEntity> workerCertificateList = workerEntity.getWorkerCertificateList();
		workerContractService.delete(new EntityWrapper<BsWorkerContractEntity>().eq("worker_id", workerEntity.getId()));
		workerCertificateService.delete(new EntityWrapper<BsWorkerCertificateEntity>().eq("worker_id", workerEntity.getId()));
		if(!CollectionUtil.isEmpty(workerContractList)) {
			for (BsWorkerContractEntity bsWorkerContractEntity : workerContractList) {
				String urlPath = "";
				String imgName = "";
				List<Map<String, Object>> attachmentListList = bsWorkerContractEntity.getAttachmentList();
		
				if(!CollectionUtil.isEmpty(attachmentListList)) {
					for (Map<String, Object> map : attachmentListList) {
						urlPath += map.get("url") + ",";
						imgName += map.get("name") + ",";
					}
				}
				if(!StrUtil.isBlank(urlPath)) {
					urlPath = urlPath.substring(0,urlPath.length()-1);
				}
				if(!StrUtil.isBlank(imgName)) {
					imgName = imgName.substring(0,imgName.length()-1);
				}
				bsWorkerContractEntity.setAttachmentUrl(urlPath);
				bsWorkerContractEntity.setAttachmentName(imgName);
				bsWorkerContractEntity.setWorkerId(workerEntity.getId());
				workerContractService.insertOrUpdate(bsWorkerContractEntity);
			}
		}
		if(!CollectionUtil.isEmpty(workerCertificateList)) {
			for (BsWorkerCertificateEntity bsWorkerCertificateEntity : workerCertificateList) {
				bsWorkerCertificateEntity.setWorkerId(workerEntity.getId());
				workerCertificateService.insertOrUpdate(bsWorkerCertificateEntity);
			}
		}
	}
	@Override
	public List<Map<String, Object>> selectWorker(Map<String, Object> params) {
		return baseMapper.selectWorker(params);
	}
	@Override
	public List<BsWorkerBean> getInfoByIdCard(Map<String, Object> params) throws ServiceException {
		return baseMapper.getInfoByIdCard(params);
	}
}
