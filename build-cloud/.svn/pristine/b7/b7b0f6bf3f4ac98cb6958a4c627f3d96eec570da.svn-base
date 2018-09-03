package com.build.cloud.modules.productplan.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.build.cloud.common.utils.FileUtil;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.fastdfs.service.IFileService;
import com.build.cloud.modules.productplan.dto.HomePage;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.sunsine.fastdfs.FastDfsInfo;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author liangsen
 * @since 2018-04-23
 */
@Controller
@RequestMapping("/pp/homePage")
public class HomePageController extends AbstractController{
    @Autowired  
    private TaskService taskService; 
    
    @Autowired
    private IFileService fileService;
    
    /**
	 * 获取劳务班组合同所有类型
	 */
	@GetMapping("/v1/info")
	@ResponseBody
	public Result info() {
		try{
			HomePage homePage = new HomePage();
			//获取任务服务对象  
	        List<Task> tasks = taskService.createTaskQuery()  
	                                    .taskInvolvedUser(ShiroUtils.getUserId())//通过接受人来查询个人任务  
	                                    .list();  
	        if(tasks!=null){
	        	homePage.setCheckCount(tasks.size());
	        }
	        
	        return Result.ok().put(homePage);
		}catch(Exception e){
			logger.error("首页查询异常", e);
   			return Result.error("首页查询异常："+e.getMessage());
		}
	}
    
//	@PostMapping("/v1/testImgUpload")
//	@ResponseBody
//	public Result testImgUpload(HttpServletRequest request) {
//		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> mMap = mRequest.getFileMap();
//		File file = null;
//		String fileAbsolutePath = "";
//		try {
//			for (String keyStr : mMap.keySet()) {
//				MultipartFile mFile = mMap.get(keyStr);
//				if (mFile != null) {
//					file = FileUtil.convert(mFile);
////					long fileSize = mFile.getSize();
////					String fileContentType = mFile.getContentType();
////					String fileName = file.getName();
////					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//					FastDfsInfo fastDfsInfo = fileService.upload(file);
//					fileAbsolutePath = fastDfsInfo.getFileAbsolutePath();
//				}
//				if (file != null) {
//					cn.hutool.core.io.FileUtil.del(file);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.error("文件上传异常");
//		}
//		
//		return Result.ok("文件上传成功").put(fileAbsolutePath);
//	}
}
