package com.build.cloud.modules.bs.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsWorkerCertificateEntity;
import com.build.cloud.modules.bs.entity.BsWorkerContractEntity;
import com.build.cloud.modules.bs.service.IBsWorkerCertificateService;
import com.build.cloud.modules.fastdfs.service.IFileService;
import com.sunsine.fastdfs.exception.FastDFSException;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/bs/certificate")
public class BsWorkerCertificateController extends AbstractController{
	
	@Autowired
	private IBsWorkerCertificateService bsWorkerCertificateService;
	@Autowired
	private IFileService fileService;

	@PostMapping("/v1/saveCertificate")
	@RequiresPermissions("bs:worker:save")
	public Result saveCertificate(@RequestBody String str) {
		List<BsWorkerCertificateEntity> list = JSON.parseArray(str, BsWorkerCertificateEntity.class);
		if(!CollectionUtil.isEmpty(list)) {
			bsWorkerCertificateService.insertBatch(list);
		}
		return Result.ok("资格证书信息新增成功");
	}
	
	@PostMapping("/v1/updateCertificate")
	@RequiresPermissions("bs:worker:update")
	public Result updateCertificate(@RequestBody BsWorkerCertificateEntity entity) {
		if(entity != null) {
			bsWorkerCertificateService.updateById(entity);
		}
		return Result.ok("修改成功");
	}
	
	@PostMapping("/v1/deleteCertificate")
	@RequiresPermissions("bs:worker:delete")
	public Result deleteCertificate(String id) {
		BsWorkerCertificateEntity entity = bsWorkerCertificateService.selectById(id);
		try {
			fileService.deleteFile("group1", entity.getAttachmentUrl());
			bsWorkerCertificateService.deleteById(id);
		} catch (FastDFSException e) {
			e.printStackTrace();
			return Result.error("资格证书文件删除失败");
		}
		return Result.ok("删除成功");
	}
	

}
