package com.build.cloud.common.activity.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.activity.pojo.ActTaskPojo;
import com.build.cloud.common.activity.pojo.ActivityImplPojo;
import com.build.cloud.common.activity.pojo.ProcessDefinitionPojo;
import com.build.cloud.common.activity.pojo.TaskIdMapPojo;
import com.build.cloud.common.constant.ActivityConstant;
import com.build.cloud.common.constant.ActivityConstant.TaskOperType;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.modules.productplan.dto.ActHiComment;
import com.build.cloud.modules.productplan.service.IActHiCommentService;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysUserService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
  
@Service("activityService")  
public class ActivityService {  
	private static Logger log = LoggerFactory.getLogger(ActivityService.class); 
	
	@Autowired
	private HistoryService historyService;
    @Autowired  
    private RuntimeService runtimeService;   
    @Autowired  
    private TaskService taskService;  
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IActHiCommentService actHiCommentService;
      
    /** 
     * 启动/部署 流程
     * @return 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
	public String start(String key) {
		//流程启动  
//        List<Deployment> deployments = repositoryService  
//                .createDeploymentQuery().deploymentName("bohui")  
//                .orderByDeploymenTime().desc().list(); 
//        if(deployments==null||deployments.size()==0){
//        if("1".equals(flag)){
//        	InputStream in = this.getClass().getResourceAsStream("/processes/test.bpmn");  
//    		Deployment dm = repositoryService.createDeployment()  
//                                              .name("test")  
//                                              .addInputStream("test.bpmn",in)
//                                              .deploy();  
//        }
        //根据key启动最新版本的流程定义的实例
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey(key);
        log.info("流程实例[key="+key+"]启动，实例id="+pi1.getProcessInstanceId());
        return pi1.getProcessInstanceId();
	}
    
    /** 
     * 启动流程并完成第一个节点
     * @return 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
	public String startAndComplete(TaskIdMapPojo pojo) {
        //根据key启动最新版本的流程定义的实例
        ExecutionEntity ee = (ExecutionEntity) runtimeService.startProcessInstanceByKey(pojo.getInsKey());
        String processInstanceId = ee.getProcessInstanceId();
        log.info("流程实例[key="+pojo.getInsKey()+"]启动，实例id="+processInstanceId);
        
        //完成第一个节点
//        ProcessInstance pi = findProcessInstance(ee.getProcessInstanceId());
//        taskService.createTaskQuery().taskDefinitionKey(pi.getActivityId()).
//        	processDefinitionId(processInstanceId).singleResult();
        completeTaskByInsId(processInstanceId,pojo);
        return processInstanceId;
	}
    
    /** 
     * 根据流程实例id完成当前节点
     * @return 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
	public void completeTaskByInsId(String processInstanceId,TaskIdMapPojo pojo) {
		Task task = taskService.createTaskQuery()
        		.processInstanceId(processInstanceId).active().singleResult();
        taskService.setAssignee(task.getId(), pojo.getUserId());
        
        completeTaskByTaskId(task.getId(),pojo);
	}

	/** 
     * 根据任务id完成流程节点,无批注
     * @return 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
	public void completeTaskByTaskId(String taskId,TaskIdMapPojo pojo) {
    	if(pojo.getLocalMap()!=null){
    		taskService.setVariablesLocal(taskId, pojo.getLocalMap());
        }
    	if(pojo.getMap()!=null){
    		taskService.setVariables(taskId, pojo.getMap());
        }
    	
    	String insId = findTaskById(taskId).getProcessInstanceId();
        taskService.complete(taskId);
        
        setParentId(insId,taskId);
	}
	
	/** 
     * 根据任务id和map完成流程节点，并批注
     * @return 
     */  
	@Transactional(propagation=Propagation.REQUIRED) 
	public void completeTaskByTaskIdAndMap(TaskIdMapPojo pojo) {
		String insId = findTaskById(pojo.getTaskId()).getProcessInstanceId();
		
		taskService.setAssignee(pojo.getTaskId(), pojo.getUserId());
		
		if(pojo.getLocalMap()!=null){
			taskService.setVariablesLocal(pojo.getTaskId(), pojo.getLocalMap());
        }
		//批注
	    if(!StringUtils.isEmpty(pojo.getComment())){
	    	comment(pojo.getTaskId(),pojo.getComment());
	    }
		taskService.complete(pojo.getTaskId(),pojo.getMap());
        
		setParentId(insId,pojo.getTaskId());
	}
	
	//activiti未设置父节点id,此处手动设置父节点id
	private void setParentId(String insId,String taskId){
		Task nextTask = taskService.createTaskQuery()
        		.processInstanceId(insId).active().singleResult();
		if(nextTask!=null){
			nextTask.setParentTaskId(taskId);
	        taskService.saveTask(nextTask);
		}
	}
	
	/** 
     * 驳回流程 
     * @param userId 用户/办理人 
     * @param taskId 当前任务ID 
     * @param activityId 驳回节点ID 
     * @param variables 流程存储参数 
     * @throws Exception 
     */  
	@Transactional(propagation=Propagation.REQUIRED) 
    public boolean backProcess(TaskIdMapPojo pojo) {  
		boolean isReturnSubmit = false;//是否回到提交节点
		
		String activityId = "";
		Task curTask = findTaskById(pojo.getTaskId());
		if(Objects.equal("pre", pojo.getActivityId())||StringUtils.isEmpty(curTask.getFormKey())){
			HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery()
					.taskId(curTask.getParentTaskId()).singleResult();
//			Task parentTask = findTaskById(curTask.getParentTaskId());
//			String userId = parentTask.getAssignee();
			activityId = parentTask.getTaskDefinitionKey();
		}else{
			activityId = curTask.getFormKey();
//			ActivityImpl activityImpl = findActivitiImpl(pojo.getTaskId(),activityId);
//			if(activityImpl==null){
//				throw new Exception("驳回节点不存在");
//			}
		}
  
        // 查找所有并行任务节点，完成节点
        List<Task> taskList = taskService.createTaskQuery()
        		.taskDefinitionKey(curTask.getTaskDefinitionKey()) 
                .list();  
        for (Task task : taskList) {  
        	if(Objects.equal(task.getId(), pojo.getTaskId())){
        		continue;
        	}
        	if(StringUtils.isEmpty(task.getExecutionId())){//任务还未完成
        		//设置驳回变量,以便历史查询
        		Map<String,Object> localMap = Maps.newHashMap();
        		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
         		taskService.setVariablesLocal(task.getId(), localMap);
         		
        		taskService.complete(task.getId());
            	comment(task.getId(),"该任务被用户["+pojo.getUserId()+"]驳回");
        	}
        	
//        	taskService.deleteTask(task.getId(),true);//true表示连带删除历史表(ACT_HI_TASKINST用户历史任务),ACT_HI_ACTINST(所有历史任务表中无法删除)
//        	historyService.deleteHistoricTaskInstance(task.getId());
        }  
        
        //当前节点转向到指定流程
        String insId = findTaskById(pojo.getTaskId()).getProcessInstanceId();
        turnTransition(pojo.getUserId(),pojo.getTaskId(),activityId,pojo.getMap());
        log.info("taskId["+pojo.getTaskId()+"]办理人["+pojo.getUserId()+"]驳回，流程转至节点["+activityId+"]");
        
        
        Task nextTask = taskService.createTaskQuery()
        		.processInstanceId(insId)
        		.active().singleResult();
        if(nextTask!=null){
        	//设置父节点id
			nextTask.setParentTaskId(pojo.getTaskId());
	        taskService.saveTask(nextTask);
	        //驳回到提交节点后，将提交节点的办理人设为变量传递的办理人
	        if(Objects.equal(nextTask.getCategory(), "submit")){
	        	//这里相当于设置提交人的代办事项,目前因为没有需求故取消设置
//	        	taskService.claim(nextTask.getId(), (String)getVariablesByTask(nextTask.getId(),"submitBy"));
	        	isReturnSubmit = true;
	        }
		}
        return isReturnSubmit;
    }  
	
	/** 
     * 中止流程 
     *  
     * @param taskId 
     */  
//    public String endProcess(@RequestBody TaskIdMapPojo pojo){  
////        ActivityImpl endActivity = findActivitiImpl(pojo.getTaskId(), "end"); 
//        turnTransition(pojo.getUserId(),pojo.getTaskId(), "end", pojo.getMap()); 
//        //业务回写
//        return "1";
//    }  
	

	/** 
	 * 查看流程定义 
	 * 查询act_re_procdef表 流程定义表 
	 */  
	public List<ProcessDefinitionPojo> queryProcdef(String key) {
		List<ProcessDefinitionPojo> resList = Lists.newArrayList();
		
	    //创建查询对象  
	    ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();  
	    //添加查询条件  
	    query.processDefinitionKey(key);//通过key获取  
	        // .processDefinitionName("My process")//通过name获取  
	        // .orderByProcessDefinitionId()//根据ID排序  
	           //.processDefinitionKeyLike(processDefinitionKeyLike)//支持模糊查询  
	            //.listPage(firstResult, maxResults)//分页  
	    //执行查询获取流程定义明细  
	    List<ProcessDefinition> pds = query.list();//获取批量的明细  
	                    //.singleResult()//获取单个的明细  
	    for (ProcessDefinition pd : pds) { 
	    	ProcessDefinitionPojo pdPojo = new ProcessDefinitionPojo();
	    	pdPojo.setId(pd.getId());
	    	pdPojo.setKey(pd.getKey());
	    	pdPojo.setDescription(pd.getDescription());
	    	pdPojo.setCategory(pd.getCategory());
	    	pdPojo.setDeploymentId(pd.getDeploymentId());
	    	pdPojo.setDiagramResourceName(pd.getDiagramResourceName());
	    	pdPojo.setName(pd.getName());
	    	pdPojo.setResourceName(pd.getResourceName());
	    	pdPojo.setSuspended(pd.isSuspended());
	    	pdPojo.setTenantId(pd.getTenantId());
	    	pdPojo.setVersion(pd.getVersion());
//	    	try{
//	    		pdPojo.setIns(repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getResourceName()));
//	    	}catch(Exception e){
//	    		e.printStackTrace();
//	    	}
	    	resList.add(pdPojo);
	    }  
		return resList;
	}

	/** 
     * 查看运行流程实例
     */  
	public ProcessInstance queryProcessInstance(String processInstanceId) {
        ProcessInstance pi = findProcessInstance(processInstanceId);
        return pi;
	}  
	
	/** 
     * 根据接受人查询该用户的任务 
     */  
    public List<ActTaskPojo> queryTaskByUser(String user){  
    	List<ActTaskPojo> acTaskList = Lists.newArrayList();
    	
        //获取任务服务对象  
        List<Task> tasks = taskService.createTaskQuery()  
                                    //模糊查询  
                                    //.taskAssigneeLike(assigneeLike)  
                                    //通过执行对象ID查询任务  
                                    //.executionId(executionId)  
                                    .taskInvolvedUser(user)//通过接受人来查询个人任务  
                                    .orderByTaskCreateTime().desc()
                                    .list();  
        for (Task task : tasks) {  
            System.out.println("ID:"+task.getId()+",姓名:"+task.getName()+",接收人:"+task.getAssignee()+",开始时间:"+task.getCreateTime());  
            ActTaskPojo actTask = new ActTaskPojo();
            actTask.setAssignee(task.getAssignee());
            //用户信息
            SysUserEntity userAssignee = sysUserService.selectById(task.getAssignee());
        	if(userAssignee!=null){
        		actTask.setAssigneeName(userAssignee.getUserName());
        	}
        	
            actTask.setUser(user);
            actTask.setId(task.getId());
            actTask.setName(task.getName());
            actTask.setCreateTime(task.getCreateTime());
            actTask.setDelegationState(task.getDelegationState());
            actTask.setTaskDefinitionKey(task.getTaskDefinitionKey());
            actTask.setCategory(task.getCategory());
            actTask.setFormKey(task.getFormKey());
            actTask.setOwner(task.getOwner());
            //设置父节点
            actTask.setParentTaskId(task.getParentTaskId());
            if(!StringUtils.isEmpty(task.getParentTaskId())){
            	HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery()
        				.taskId(task.getParentTaskId()).singleResult();
            	actTask.setParentAssignee(parentTask.getAssignee());
            	//查询用户信息
            	SysUserEntity userEntity = sysUserService.selectById(parentTask.getAssignee());
            	if(userEntity!=null){
            		actTask.setParentAssigneeName(userEntity.getUserName());
            	}
            }
            String billCode = (String)getVariablesByTask(task.getId(), 
            		ActivityConstant.VariableKey.ACT_VAR_BILLCODE.value);
            String billType = (String)getVariablesByTask(task.getId(), 
            		ActivityConstant.VariableKey.ACT_VAR_BILLTYPE.value);
            String submiter = (String)getVariablesByTask(task.getId(), 
            		ActivityConstant.VariableKey.ACT_VAR_SUBMITER.value);
            String projectName = (String)getVariablesByTask(task.getId(), 
            		ActivityConstant.VariableKey.ACT_VAR_PROJECTNAME.value);
            actTask.setBillCode(billCode);
            actTask.setBillType(billType);
            actTask.setSubmitBy(submiter);
            actTask.setProjectName(projectName);
            
            acTaskList.add(actTask);
        }  
        
        return acTaskList;
    }  
    
    /**
     * 使用processInstanceId查询历史流程中各节点 (只包含userTask)的信息
     */
    public List<ActivityImplPojo> findHistoryActivitiImpl(String processInstanceId) { 
    	List<ActivityImplPojo> resList = Lists.newArrayList();
    	
    	List<HistoricTaskInstance> list = historyService // 历史相关Service
                .createHistoricTaskInstanceQuery() // 创建历史活动实例查询
                .processInstanceId(processInstanceId)// 执行流程实例id
                .orderByTaskCreateTime().desc() 
//                .finished()
                .list();
    	for (HistoricTaskInstance historicTaskInstance : list) {
    		//查询历史userTask,并获得执行当时节点的本地变量,为后续判断是不是驳回节点
//    		HistoricTaskInstance hisTask = historyService.createHistoricTaskInstanceQuery()
//    				.taskId(historicActivityInstance.getTaskId()).singleResult();
//    		String backStatus = "0";
//    		if(hisTask.getTaskLocalVariables().get("backStatus")!=null){
//    			backStatus = (String)hisTask.getTaskLocalVariables().get("backStatus");
//    		}
    		//排除当前正在执行task
    		if(historicTaskInstance.getEndTime()==null){
    			continue;
    		}
    		ActivityImplPojo pojo = new ActivityImplPojo();
    		pojo.setTaskId(historicTaskInstance.getId());
    		pojo.setType("userTask");
    		pojo.setStartTime(historicTaskInstance.getStartTime());
    		pojo.setEndTime(historicTaskInstance.getEndTime());
    		pojo.setAssignee(historicTaskInstance.getAssignee());
    		//设置操作类型
    		HistoricVariableInstance oper = historyService.createHistoricVariableInstanceQuery()
    		.taskId(historicTaskInstance.getId()).variableName("oper").singleResult();
    		if(oper!=null&&oper.getValue()!=null){
    			pojo.setOperType(oper.getValue().toString());
    			TaskOperType operType = ActivityConstant.TaskOperType.getByValue(oper.getValue().toString());
    			if(operType!=null){
    				pojo.setOperTypeName(operType.getName());
    			}
    		}
    		
    		//用户信息
            SysUserEntity userAssignee = sysUserService.selectById(historicTaskInstance.getAssignee());
        	if(userAssignee!=null){
        		pojo.setAssigneeName(userAssignee.getUserName());
        	}
    		
    		Long durationLong = historicTaskInstance.getDurationInMillis();
    		pojo.setDuration(DateUtils.formatDuring(durationLong));
    		//获取父节点(发送人)
    		String parentTaskId = historicTaskInstance.getParentTaskId();
    		if(!StringUtils.isEmpty(parentTaskId)){
    			HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery()
        				.taskId(historicTaskInstance.getParentTaskId()).singleResult();
        		pojo.setParentAssignee(parentTask.getAssignee());
        		//用户信息
                SysUserEntity parentUser = sysUserService.selectById(parentTask.getAssignee());
            	if(parentUser!=null){
            		pojo.setParentAssigneeName(parentUser.getUserName());
            	}
    		}else{//如果没有发送人,则发送人=经办人
    			pojo.setParentAssigneeName(userAssignee.getUserName());
    		}
    		//获取批注
//    		List<Comment> comList = findCommentByTaskId(historicTaskInstance.getId());
//    		if(comList!=null&&comList.size()>0){
//    			pojo.setComment(comList.get(0).getFullMessage());
//    		}
    		//开发数据库中fullmessage无乱码,测试数据库中乱码。
    		//API又没有getMessge方法,只能自己查表(message无乱码问题)
    		EntityWrapper<ActHiComment> ew = new EntityWrapper<>();
    		ew.eq("TASK_ID_", historicTaskInstance.getId());
    		ew.eq("TYPE_", "comment");
    		List<ActHiComment> comList = actHiCommentService.selectList(ew);
    		if(comList!=null&&comList.size()>0){
    			pojo.setComment(comList.get(0).getMessage());
    		}
    		resList.add(pojo);
		}
    	return resList;
	}  
    
    /**
     * 使用processInstanceId查询流程定义中各节点的信息
     */
    public List<ActivityImplPojo> findActivitiImpl(String processInstanceId){ 
    	List<ActivityImplPojo> resList = Lists.newArrayList();
    	
    	String actKey = null;
    	List<Task> taskList = taskService.createTaskQuery()
        		.processInstanceId(processInstanceId).active().list();
    	if(taskList!=null&&taskList.size()>0){
    		actKey = taskList.get(0).getTaskDefinitionKey();
    	}
    	
    	ProcessInstance pi = runtimeService  
                //创建执行对象查询,查询正在执行的执行对象  
                //.createExecutionQuery()  
                .createProcessInstanceQuery()//创建流程实例查询,查询正在执行的流程实例  
                .processInstanceId(processInstanceId)//通过流程实例ID查询  
                //返回批量结果  
                //.list()  
                .singleResult();//返回唯一的结果  
    	String processDefinitionId = null;
    	if(pi==null){
    		processDefinitionId = historyService.createHistoricProcessInstanceQuery()
    								.processInstanceId(processInstanceId).singleResult()
    								.getProcessDefinitionId();
    	}else{
    		processDefinitionId = pi.getProcessDefinitionId();
    	}
    	
    	
    	ReadOnlyProcessDefinition entity = ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
    	ProcessDefinitionEntity proEntity = (ProcessDefinitionEntity)entity;
    	List<ActivityImpl> list = proEntity.getActivities();
    	Integer actIndex = -1;
    	Integer i = 1; 
    	for (ActivityImpl activityImpl : list) {
    		String name = (String)activityImpl.getProperties().get("name");
    		if (StringUtil.equalsIgnoreCase("end", name)) {
    			name = "结束";
    		}
    		String type = (String)activityImpl.getProperties().get("type");
    		
    		ActivityImplPojo pojo = new ActivityImplPojo();
    		//设置已完成节点坐标,其本身是未完成,前面的均为已完成
    		if(Objects.equal(actKey, activityImpl.getId())){
    			actIndex = i;
    		}
    		if(Objects.equal(ActivityConstant.TaskDefType.ACT_TASKDEF_STARTEVENT.value, type)){
    			pojo.setIndex(0);
    		}else if(Objects.equal(ActivityConstant.TaskDefType.ACT_TASKDEF_ENDEVENT.value, type)){
    			pojo.setIndex(999);
    		}else{
    			pojo.setIndex(i);
    			i++;
    		}
    		pojo.setActivityId(activityImpl.getId());
    		pojo.setName(name);
    		pojo.setType(type);
    		resList.add(pojo);
		}
    	
    	//循环，设置已完成节点之前的节点，均为已完成
    	for (ActivityImplPojo pojo : resList) {
    		if(pojo.getIndex() < actIndex||actKey==null){
    			pojo.setFinished(true);
    		}
		}
    	
//	    ActivityImpl activityImpl =  proEntity.findActivity("jlCheck");    
	    return resList;    
	}  
    
    public List<String> findNowChecker(String insId){
    	List<String> checkerList = Lists.newArrayList();
    	
    	List<Task> taskList = taskService.createTaskQuery()
        		.processInstanceId(insId).active().list();
    	for (Task task : taskList) {
    		//当不是提交节点的时候才设置当前审核人
    		if(!Objects.equal("submit", task.getCategory())){
    			checkerList.add(task.getAssignee());
    		}
		}
    	return checkerList;
    }
    
    /**
     * 
     * 查询流程批注信息
     */
    public List<Comment> findCommentByTaskId(String taskId) {  
        List<Comment> list = taskService.getTaskComments(taskId);  
        return list;  
    }  
    
      
    /**
     * 获取流程的图片
     * @throws IOException 
     */
    public void testGetProcDefImg(String deploymentId) throws IOException{
        //从act_ge_bytearray表中获取数据，该表存放的是我们的流程定义文件和图片文件的数据
        List<String> resources=repositoryService.getDeploymentResourceNames(deploymentId);
        
        //获取图片的名称
        String imgName="";
        if(resources!=null&&resources.size()>0){
            for (String string : resources) {
                if(string.indexOf("png")>=0){
                    imgName=string;
                }
            }
        }
        System.out.println(imgName);
        if(imgName!=null){
            File f=new File("d:/tt.png");
            
            InputStream in=repositoryService.getResourceAsStream(deploymentId, imgName);
            
            FileUtils.copyInputStreamToFile(in, f);
            
        }
    }
    
    public List<Task> getNowTaskList(String insId){
    	List<Task> taskList = taskService.createTaskQuery()
        		.processInstanceId(insId).active().list();
    	return taskList;
    }
    
    public String getNextTaskType(String insId){
    	String type = null;
    	
    	List<Task> taskList = taskService.createTaskQuery()
        		.processInstanceId(insId).active().list();
    	if(taskList!=null&&taskList.size()>0){
    		ActivityImpl ai = findActivitiImpl(taskList.get(0).getId(),null);
        	
        	List<PvmTransition> outTransitions = ai.getOutgoingTransitions(); 
        	if(outTransitions!=null&&outTransitions.size()>0){
        		type = (String)outTransitions.get(0).getDestination().getProperty("type");
        	}
    	}
    	
    	return type;
    }
    
    public String getEndChecker(String insId){
    	String endChecker = null;
    	List<HistoricTaskInstance> historyTaskList = historyService.createHistoricTaskInstanceQuery()
    			.processInstanceId(insId)
    			.orderByHistoricTaskInstanceEndTime().desc().list();
    	
    	if(historyTaskList!=null&&historyTaskList.size()>0){
    		endChecker = historyTaskList.get(0).getAssignee();
    	}
    	return endChecker;
    }
    
    /**
     * 获取变量值
     * @param taskId
     * @param variableName
     * @return
     */
    private Object getVariablesByTask(String taskId,String variableName){  
        Object variable = taskService.getVariable(taskId, variableName);  
        return variable;  
    }  
    
    /**
     * 获取节点变量值
     * @param taskId
     * @param variableName
     * @return
     */
    private Object getLocalVariablesByTask(String taskId,String variableName){  
        Object variable = taskService.getVariableLocal(taskId, variableName);  
        return variable;  
    } 
    
    /**
     * 设置节点变量值
     * @param taskId
     * @param variableName
     * @return
     */
//    private void setLocalVariablesByTask(String taskId,String variableName,Object value){  
//        taskService.setVariableLocal(taskId, variableName, value);  
//    } 
    
    /**
     * 添加流程批注信息
     */
    @Transactional(propagation=Propagation.REQUIRED) 
    private void comment(String taskId, String message){
    	Task task =  findTaskById(taskId);  
    	taskService.addComment(taskId, task.getProcessInstanceId(), message);
    }
    
    /** 
     * 根据流程实例id获得流程实例
     */  
	public ProcessInstance findProcessInstance(String processInstanceId) {
        ProcessInstance pi = runtimeService  
                    //创建执行对象查询,查询正在执行的执行对象  
                    //.createExecutionQuery()  
                    .createProcessInstanceQuery()//创建流程实例查询,查询正在执行的流程实例  
                    .processInstanceId(processInstanceId)//通过流程实例ID查询  
                    //返回批量结果  
                    //.list()  
                    .singleResult();//返回唯一的结果  
        return pi;
	}  
	
    /** 
     * 根据任务ID获得任务实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private TaskEntity findTaskById(String taskId){  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();  
//        if (task == null) {  
//            throw new Exception("任务实例未找到!");  
//        }  
        return task;  
    }  
    
    /** 
     * 根据任务ID获取流程定义 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId){
    	ReadOnlyProcessDefinition entity = ((RepositoryServiceImpl)repositoryService)
    			.getDeployedProcessDefinition(findTaskById(taskId).getProcessDefinitionId());
    	ProcessDefinitionEntity proEntity = (ProcessDefinitionEntity)entity;
    	return proEntity;
    }
    
    /** 
     * 根据任务ID和节点ID获取活动节点 <br> 
     *  
     * @param taskId 
     *            任务ID 
     * @param activityId 
     *            活动节点ID <br> 
     *            如果为null或""，则默认查询taskId对应活动节点 <br> 
     *            如果为"end"，则查询结束节点 <br> 
     *  
     * @return 
     * @throws Exception 
     */  
    private ActivityImpl findActivitiImpl(String taskId, String activityId){  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
  
        // 获取当前活动节点ID  
        if (StringUtils.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition).findActivity(activityId);  
  
        return activityImpl;  
    }  
    
    /** 
     * 流程转向操作 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            目标节点任务ID 
     * @param variables 
     *            流程变量 
     * @throws Exception 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
    private void turnTransition(String userId, String taskId, String activityId,Map<String, Object> variables){  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        //设置驳回变量,以便历史查询
        if(!"end".equalsIgnoreCase(activityId)){
        	Map<String,Object> localMap = Maps.newHashMap();
    		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_REJECT.value);
     		taskService.setVariablesLocal(taskId, localMap);
        }else{
        	Map<String,Object> localMap = Maps.newHashMap();
    		localMap.put("oper", ActivityConstant.TaskOperType.ACT_TASKOPER_PASS.value);
     		taskService.setVariablesLocal(taskId, localMap);
        }
        taskService.setAssignee(taskId, userId);
        taskService.complete(taskId, variables);  
        
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  
    
    /** 
     * 清空指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @return 节点流向集合 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = Lists.newArrayList();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    }  
    
    /** 
     * 还原指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @param oriPvmTransitionList 
     *            原有节点流向集合 
     */  
    @Transactional(propagation=Propagation.REQUIRED) 
    private void restoreTransition(ActivityImpl activityImpl,List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  
    
    /**  
     * 下一个任务节点信息,  
     *  
     * 如果下一个节点为用户任务则直接返回,  
     *  
     * 如果下一个节点为排他网关, 获取排他网关Id信息, 根据排他网关Id信息和execution获取流程实例排他网关Id为key的变量值,  
     * 根据变量值分别执行排他网关后线路中的el表达式, 并找到el表达式通过的线路后的用户任务信息  
     * @param ActivityImpl activityImpl     流程节点信息  
     * @param String activityId             当前流程节点Id信息  
     * @param String elString               排他网关顺序流线段判断条件, 例如排他网关顺序留线段判断条件为${money>1000}, 若满足流程启动时设置variables中的money>1000, 则流程流向该顺序流信息  
     * @param String processInstanceId      流程实例Id信息  
     * @return  
     */    
    private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl,String activityId,String elString, String processInstanceId){   
              
        PvmActivity ac = null;  
          
        Object s = null;  
          
        //获取节点所有流向线路信息   
        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();    
        List<PvmTransition> outTransitionsTemp = null;    
        for(PvmTransition tr : outTransitions){      
            ac = tr.getDestination(); //获取线路的终点节点      
            //如果流向线路为排他网关   
            if(ActivityConstant.TaskDefType.ACT_TASKDEF_EXGATE.value.equals(ac.getProperty("type"))){    
                outTransitionsTemp = ac.getOutgoingTransitions();  
                  
                //如果网关路线判断条件为空信息   
                if(StringUtils.isEmpty(elString)) {  
                    //获取流程启动时设置的网关判断条件信息   
                    elString = getGatewayCondition(ac.getId(), processInstanceId);  
                }  
                  
                //如果排他网关只有一条线路信息   
                if(outTransitionsTemp.size() == 1){    
                    return nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId, elString, processInstanceId);    
                }else if(outTransitionsTemp.size() > 1){  //如果排他网关有多条线路信息   
                    for(PvmTransition tr1 : outTransitionsTemp){    
                        s = tr1.getProperty("conditionText");  //获取排他网关线路判断条件信息   
                        //判断el表达式是否成立   
                        if(isCondition(ac.getId(), elString,s==null?null:s.toString())){    
                            return nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId, elString, processInstanceId);    
                        }    
                    }    
                }    
            }else if(ActivityConstant.TaskDefType.ACT_TASKDEF_USERTASK.value.equals(ac.getProperty("type"))
            		||ActivityConstant.TaskDefType.ACT_TASKDEF_ENDEVENT.value.equals(ac.getProperty("type"))){    
                return ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();    
            }else{    
            }    
        }     
        return null;    
    }  
    
    /** 
     * 查询流程启动时设置排他网关判断条件信息  
     * @param String gatewayId          排他网关Id信息, 流程启动时设置网关路线判断条件key为网关Id信息  
     * @param String processInstanceId  流程实例Id信息  
     * @return 
     */  
    private String getGatewayCondition(String gatewayId, String processInstanceId) {  
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();  
        return runtimeService.getVariable(execution.getId(), gatewayId).toString();  
    }  
    
    /** 
     * 根据key和value判断el表达式是否通过信息  
     * @param String key    el表达式key信息  
     * @param String el     el表达式信息  
     * @param String value  el表达式传入值信息  
     * @return 
     */  
    private boolean isCondition(String key, String el, String value) {  
        ExpressionFactory factory = new ExpressionFactoryImpl();    
        SimpleContext context = new SimpleContext();    
        context.setVariable(key, factory.createValueExpression(value, String.class));    
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);    
        return (Boolean) e.getValue(context);  
    }  
}  