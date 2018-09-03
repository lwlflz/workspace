package com.build.cloud.modules.sys.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.utils.FileUtil;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.Query;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.fastdfs.service.IFileService;
import com.build.cloud.modules.sys.dao.SysFileUploadDao;
import com.build.cloud.modules.sys.entity.SysFileEntityEntity;
import com.build.cloud.modules.sys.entity.SysFileUploadEntity;
import com.build.cloud.modules.sys.service.ISysFileEntityService;
import com.build.cloud.modules.sys.service.ISysFileUploadService;
import com.sunsine.common.util.Md5CaculateUtil;
import com.sunsine.fastdfs.FastDfsInfo;
@Service("sysFileUploadService")
public class SysFileUploadServiceImpl extends ServiceImpl<SysFileUploadDao, SysFileUploadEntity>
		implements ISysFileUploadService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISysFileEntityService sysFileEntityService;
	@Autowired
	private IFileService fileService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<SysFileUploadEntity> page =
			this.selectPage(new Query<SysFileUploadEntity>(params).getPage(), new EntityWrapper<SysFileUploadEntity>());
		return new PageUtils(page);
	}
	@Transactional
	@Override
	public String save(SysFileUploadEntity sysFileUpload, SysFileEntityEntity fileEntityEntity) {
		sysFileEntityService.insert(fileEntityEntity);
		sysFileUpload.setFileId(fileEntityEntity.getFileId());
		baseMapper.insert(sysFileUpload);
		return sysFileUpload.getId();
	}
	@Override
	public Result upload(HttpServletRequest request) {
		SysFileUploadEntity fileUpload = new SysFileUploadEntity();
		SysFileEntityEntity fileEntity = new SysFileEntityEntity();
		String fileId = "";
		File file = null;
		String fileAbsolutePath = "";
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
			Map<String, MultipartFile> pMap = mRequest.getFileMap();
			for (String keyStr : pMap.keySet()) {
				MultipartFile mFile = pMap.get(keyStr);
				if (mFile != null) {
					file = FileUtil.convert(mFile);
					String fileMd5 = Md5CaculateUtil.getFileMD5(file);
					SysFileEntityEntity fileE =
						sysFileEntityService
								.selectOne(new EntityWrapper<SysFileEntityEntity>().eq("file_md5", fileMd5));
					if (fileE != null) {
						cn.hutool.core.io.FileUtil.del(file);
						fileAbsolutePath = Constant.FILE_SERVICE + fileE.getFilePath();
						continue;
					}
					long fileSize = mFile.getSize();
					String fileContentType = mFile.getContentType();
					String fileName = file.getName();
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					logger.info("文件MD5为：{}，文件大小为：{}，文件类型为：{}", fileMd5, fileSize, fileContentType);
					FastDfsInfo fastDfsInfo = fileService.upload(file.getPath());
					fileUpload.setFileName(fileName);
					fileUpload.setFileType(FileUtil.getFileType(fileName));
					fileEntity.setFilePath(fastDfsInfo.getPath());
					fileEntity.setFileMd5(fileMd5);
					fileEntity.setFileSize(fileSize);
					fileEntity.setFileContentType(fileContentType);
					fileEntity.setFileExtension(fileExtension);
					fileAbsolutePath = fastDfsInfo.getFileAbsolutePath();
				}
				fileId = this.save(fileUpload, fileEntity);
				if (file != null) {
					cn.hutool.core.io.FileUtil.del(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok("文件上传成功").put(fileId);
	}
}
