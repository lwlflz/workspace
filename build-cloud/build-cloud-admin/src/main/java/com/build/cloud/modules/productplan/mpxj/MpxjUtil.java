package com.build.cloud.modules.productplan.mpxj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Relation;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.google.common.base.Objects;

public class MpxjUtil {
	private static Logger logger = LoggerFactory.getLogger(MpxjUtil.class);

	public static void main(String[] args) {
		try {
			readFile(new FileInputStream("d:/test.mpp"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<ProPlanDetail> readFile(InputStream ins) throws Exception{

        List<ProPlanDetail> taskList = new ArrayList<ProPlanDetail>();
        MPPReader mppRead = new MPPReader();
        ProjectFile pf = mppRead.read(ins);

        List<Task> tasks = pf.getAllTasks();
        logger.info("MPXJUtils.method [readFile]: taskSize-" + tasks.size());

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            //去掉第一行工程总体信息
            if(Objects.equal(task.getUniqueID(), 0)){
            	continue;
            }
            //去掉可能的空行
            if(StringUtils.isEmpty(task.getName())&&task.getDuration()==null&&task.getStart()==null){
            	continue;
            }
            
            Integer taskId = task.getID();
            String taskName = task.getName();
            String property = task.getText(1);
            String quantities = task.getText(2);
            String unit = task.getText(3);
            Integer uniqueId = task.getUniqueID();
            Integer outlineLevel = task.getOutlineLevel();
            Integer parentUniqueId = 0;
            double duration = task.getDuration().getDuration();
            Date beginDate = task.getStart();
            Date endDate = task.getFinish();
            
            if(task.getParentTask()!=null){
            	parentUniqueId = task.getParentTask().getUniqueID();
            }
            
            List<Relation> predecessors = task.getPredecessors();                              
            StringBuffer preUniqueIds = new StringBuffer();
            if(predecessors != null && predecessors.size() > 0){
                for(Relation relation : predecessors){
                    Integer targetUniqueId = relation.getTargetTask().getUniqueID();
                    String type = relation.getType().toString();
                    Double interval = relation.getLag().getDuration();
                    String pre = targetUniqueId + "-" + type + "-" + interval;
                    if(preUniqueIds.length() == 0){
                    	preUniqueIds.append(pre);
                    }else{
                    	preUniqueIds.append(","+pre);
                    }
                }
            }

            ProPlanDetail taskInfo = new ProPlanDetail();
            taskInfo.setWbsName(taskName);//wbs名称
            taskInfo.setNameProperty(property);//名称属性
//            taskInfo.setQuantities(quantities);//工程量
            taskInfo.setUnit(unit);//单位
            taskInfo.setUniqueId(uniqueId.toString());//唯一标识号
            taskInfo.setOutlineLevel(outlineLevel);//行等级
//            taskInfo.setDuration(duration);//工期
            taskInfo.setPlanBeginDate(beginDate);
            taskInfo.setPlanEndDate(endDate);
            taskInfo.setParentId(parentUniqueId.toString());//父行唯一标识
            taskInfo.setPreId(preUniqueIds.toString());//前置行唯一标识号
            
            String info = "MPXJUtils.method [readFile] taskInfo:" 
                    + "id[" + taskId + "]," 
                    + "名称[" + taskName + "]," 
                    + "唯一id[" + uniqueId + "]," 
                    + "行等级[" + outlineLevel + "]," 
                    + "父任务唯一id[" + parentUniqueId + "]," 
                    + "工期[" + duration + "]," 
                    + "开始时间[" + beginDate + "]," 
                    + "结束时间[" + endDate + "]," 
            		+ "前置[" + preUniqueIds.toString() + "]"
                    + "["+task.getText(1)+"]["+task.getText(2)+ "]["+task.getText(3)+"]";
            logger.info(info);

            taskList.add(taskInfo);             
        }               
        return taskList;
    }
}
