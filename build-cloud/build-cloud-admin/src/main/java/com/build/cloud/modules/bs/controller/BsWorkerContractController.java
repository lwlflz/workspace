package com.build.cloud.modules.bs.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.build.cloud.common.utils.FileUtil;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsWorkerCertificateEntity;
import com.build.cloud.modules.bs.entity.BsWorkerContractEntity;
import com.build.cloud.modules.bs.service.IBsWorkerContractService;
import com.build.cloud.modules.fastdfs.service.IFileService;
import com.sunsine.fastdfs.FastDfsInfo;
import com.sunsine.fastdfs.exception.FastDFSException;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/bs/contract")
public class BsWorkerContractController extends AbstractController{

	@Autowired
	private IBsWorkerContractService bsWorkerContractService;
	@Autowired
	private IFileService fileService;
	
	@PostMapping("/v1/saveContract")
	@RequiresPermissions("bs:worker:save")
	public Result saveContract(@RequestBody String str) {
		List<BsWorkerContractEntity> list = JSON.parseArray(str, BsWorkerContractEntity.class);
		if(!CollectionUtil.isEmpty(list)) {
			bsWorkerContractService.insertBatch(list);
		}
		return Result.ok();
	}
	
	@PostMapping("/v1/updateContract")
	@RequiresPermissions("bs:worker:update")
	public Result updateContract(@RequestBody BsWorkerContractEntity entity) {
		if(entity != null) {
			bsWorkerContractService.updateById(entity);
		}
		return Result.ok("修改成功");
	}
	
	@PostMapping("/v1/deleteContract")
	@RequiresPermissions("bs:worker:delete")
	public Result deleteCertificate(String id) {
		BsWorkerContractEntity entity = bsWorkerContractService.selectById(id);
		try {
			fileService.deleteFile("group1", entity.getAttachmentUrl());
			bsWorkerContractService.deleteById(id);
		} catch (FastDFSException e) {
			e.printStackTrace();
			return Result.error("合同附件删除失败");
		}
		return Result.ok("删除成功");
	}
	
}
