package com.build.cloud.common.activity.controller;

import java.io.IOException;
import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.activity.pojo.ActTaskPojo;
import com.build.cloud.common.activity.pojo.ActivityImplPojo;
import com.build.cloud.common.activity.pojo.ProcessDefinitionPojo;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.activity.service.ActivityService;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sys.shiro.ShiroUtils;

  
@RestController  
@RequestMapping("/act/activity")  
public class ActivityController extends AbstractController{  
	@Autowired
	private ActivityService activityService;
	
	
    /** 
     * 启动/部署 流程
     * @return 
     */  
//    @RequestMapping(value="/deploy",method=RequestMethod.GET)  
    @RequestMapping(value="/start",method=RequestMethod.POST)  
    public String start(String key){
    	return activityService.start(key);
    }  
    
    /** 
     * 启动流程并完成第一个节点
     * @return 
     */  
    @RequestMapping(value="/startAndComplete",method=RequestMethod.POST)  
	public String startAndComplete(@RequestBody TaskIdMapPojo pojo) {
		return activityService.startAndComplete(pojo);
	}
    
    /** 
     * 根据流程实例id完成当前节点
     * @return 
     */  
//	public void completeTaskByInsId(String insId,String userId) {
//		activityService.completeTaskByInsId(insId, userId);
//	}
    
    /** 
     * 根据任务id完成流程节点
     * @return 
     */  
//    @RequestMapping(value="/completeTaskByTaskId",method=RequestMethod.POST)  
//    public void completeTaskByTaskId(String taskId){
//    	activityService.completeTaskByTaskId(taskId);
//    }  
//    
    /** 
     * 根据任务id和map完成流程节点，并批注
     * @return 
     */  
    @RequestMapping(value="/completeTaskByTaskIdAndMap",method=RequestMethod.POST)  
    public void completeTaskByTaskIdAndMap(@RequestBody TaskIdMapPojo pojo){
    	activityService.completeTaskByTaskIdAndMap(pojo);
    }  
    
    /** 
     * 驳回流程 
     * @param userId 用户/办理人 
     * @param taskId 当前任务ID 
     * @param activityId 驳回节点ID 
     * @param map 流程存储参数 
     * @throws Exception 
     */  
    @RequestMapping(value="/backProcess",method=RequestMethod.POST)  
    public void backProcess(@RequestBody TaskIdMapPojo pojo){
    	activityService.backProcess(pojo);
    }
    
    /** 
     * 中止流程 
     *  
     * @param taskId 
     */ 
//    @RequestMapping(value="/endProcess",method=RequestMethod.POST)
//    @ResponseBody
//    public Result endProcess(@RequestBody TaskIdMapPojo pojo){
//    	try{
//    		activityService.endProcess(pojo);
//    		return Result.ok();
//    	}catch(Exception e){
//    		log.error("中止流程失败", e);
//    		return Result.error("中止流程失败,msg:"+e.getMessage());
//    	}
//    }
    
    /** 
     * 查看流程定义 
     * 查询act_re_procdef表 流程定义表 
     */  
    @RequestMapping(value="/queryProcdef",method=RequestMethod.POST)
    @ResponseBody
    public List<ProcessDefinitionPojo> queryProcdef(String key){
    	return activityService.queryProcdef(key);
    }
    
    /** 
     * 查看流程
     */  
    @RequestMapping(value="/queryProcessInstance",method=RequestMethod.POST)  
    public ProcessInstance queryProcessInstance(String processInstanceId){
    	return activityService.queryProcessInstance(processInstanceId);
    }
    
    /** 
     * 根据接受人查询该用户的任务 
     */  
    @RequestMapping(value="/v1/selectTaskByUser",method=RequestMethod.GET)  
    @ResponseBody
    public Result queryTaskByUser(){
    	try{
    		List<ActTaskPojo> resList = activityService.queryTaskByUser(ShiroUtils.getUserId());
    		return new Result().put(resList);
    	}catch(Exception e){
    		logger.error("查询用户任务失败", e);
    		return Result.error("查询用户任务失败");
    	}
    	
    }
    
    /**
     * 使用processInstanceId查询流程定义中各节点的信息
     */
    @RequestMapping(value="/v1/selectDef",method=RequestMethod.GET)  
    @ResponseBody
    public Result findActivitiImpl(@RequestParam String insId){
    	try{
	    	List<ActivityImplPojo> resList = activityService.findActivitiImpl(insId);
	    	return new Result().put(resList);
    	}catch(Exception e){
    		logger.error("查询流程定义节点失败", e);
    		return Result.error("查询流程定义节点失败");
    	}
    }
    
    /**
     * 使用processInstanceId查询历史流程中各节点的信息
     */
    @RequestMapping(value="/v1/selectHisTask",method=RequestMethod.GET)  
    @ResponseBody
    public Result findHistoryActivitiImpl(@RequestParam String insId){
    	try{
	    	List<ActivityImplPojo> resList = activityService.findHistoryActivitiImpl(insId);
	    	return new Result().put(resList);
    	}catch(Exception e){
    		logger.error("查询历史流程节点失败", e);
    		return Result.error("查询历史流程节点失败");
    	}
    }
    
    /**
     * 
     * 查询流程批注信息
     */
//    @RequestMapping(value="/findCommentByTaskId",method=RequestMethod.POST)
//    @ResponseBody
//    public List<Comment> findCommentByTaskId(String taskId){
//    	return activityService.findCommentByTaskId(taskId);
//    }
    
    
    /**
     * 获取流程的图片
     * @throws IOException 
     */
    @RequestMapping(value="/testGetProcDefImg",method=RequestMethod.POST)  
    public void testGetProcDefImg(String deploymentId) throws IOException{
    	activityService.testGetProcDefImg(deploymentId);
    }
    
    /**
     * 获取变量值
     * @param taskId
     * @param variableName
     * @return
     */
//    @RequestMapping(value="/getVariablesByTask",method=RequestMethod.POST) 
//    @ResponseBody
//    public Object getVariablesByTask(String taskId,String variableName){
//    	return activityService.getVariablesByTask(taskId, variableName);
//    }
}  