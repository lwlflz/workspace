package com.build.cloud.common.activity.listener;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaksListener2 implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Autowired
//	private RestTemplate restTemplate;

	public void notify(DelegateTask delegateTask) {
//		restTemplate.postForObject("", "", String.class);
		System.out.println("delegateTask.getEventName() = " + delegateTask.getEventName());

		delegateTask.setAssignee("ls1");
	}

}
