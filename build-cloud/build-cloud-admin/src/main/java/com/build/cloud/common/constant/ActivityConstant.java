package com.build.cloud.common.constant;

import com.google.common.base.Objects;

public class ActivityConstant {
	/**
	 * 流程定义key
	 * @author liangsen
	 *
	 */
	public enum ProcessKey{
		ACT_DEF_PRO_PLAN("proplan"),//生产计划
		ACT_DEF_PRO_CON("procon"),//合同
		ACT_DEF_PU_CON("pucon"),//采购合同
		ACT_DEF_PU_ORDER("puorder"),//采购订单
		ACT_DEF_PU_STOCKIN("pustockin"), //入库
		ACT_DEF_PU_STOCKOUT("pustockout"),//出库
		ACT_DEF_PU_TAKE("putake"), //盘存
		ACT_DEF_PU_PLAN("puplan"), //月度采购计划
		ACT_DEF_CON_LABOR("conlabor"), //劳务分包合同
		ACT_DEF_PU_PLAN_SUM("puplansum"); //月度采购计划汇总
		public String value;
		private ProcessKey(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 单据类型
	 * @author liangsen
	 *
	 */
	public enum BillType{
		ACT_BILL_PRO_PLAN("billProPlan"),//生产计划
		ACT_BILL_PRO_CON("workerTeamCon"),//劳务班组合同
		ACT_BILL_PU_PLAN("material:monthlyPurchase"), //月度采购计划
		ACT_BILL_PU_ORDER("material:purchaseOrder"), //采购订单
		ACT_BILL_PU_STOCKIN("material:inOrder"),//入库
		ACT_BILL_PU_STOCKOUT("material:outOrder"),//出库
		ACT_BILL_PU_TAKE("material:fieldInventory"),//盘存
		ACT_BILL_PU_CON("materialPurCon"),//采购合同
		ACT_BILL_PU_PLAN_SUM("material:purchaseSummary"),//月度采购计划汇总
		ACT_BILL_CON_LABOR("labourSubCon");//劳务分包合同
		public String value;
		private BillType(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 流程变量key
	 * @author liangsen
	 *
	 */
	public enum VariableKey{
		ACT_VAR_BILLTYPE("billType"),//单据类型
		ACT_VAR_BILLCODE("billCode"),//单据id
		ACT_VAR_SUBMITER("submitBy"),//提交人
		ACT_VAR_PROJECTNAME("projectName");//项目名称
		
		public String value;
		private VariableKey(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 流程变量key
	 * @author liangsen
	 *
	 */
	public enum TaskDefType{
		ACT_TASKDEF_USERTASK("userTask"),//用户任务
		ACT_TASKDEF_STARTEVENT("startEvent"),//开始节点
		ACT_TASKDEF_EXGATE("exclusiveGateway"),//排他网关
		ACT_TASKDEF_ENDEVENT("endEvent");//结束节点
		
		public String value;
		private TaskDefType(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 操作类型
	 * @author liangsen
	 *
	 */
	public enum TaskOperType{
		ACT_TASKOPER_SUBMIT("submit","提交"),//提交
		ACT_TASKOPER_PASS("pass","通过"),//通过
		ACT_TASKOPER_REJECT("reject","驳回");//驳回
		
		public String value;
		public String name;
		private TaskOperType(String value,String name){
			this.value = value;
			this.name = name;
		}
		public String getValue(){
			return this.value;
		}
		public String getName(){
			return this.name;
		}
		
		public static TaskOperType getByValue(String value) {
			for (TaskOperType operType : TaskOperType.values()) {
				if (Objects.equal(value, operType.getValue())) {
					return operType;
				}
			}
			return null;
		}
	}
}
