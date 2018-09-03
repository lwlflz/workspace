package com.build.cloud.common.constant;

import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.sunsine.common.util.EPlatform;
import com.sunsine.common.util.OSInfo;
/**
 * @ClassName: Constant
 * @Description: 常量
 * @author: liutao
 * @date: 2018年3月16日 下午2:58:08
 */
public class Constant {
	/** 超级管理员ID */
	public static final String SUPER_ADMIN = "1";
	/** 内置角色 */
	public static final String SYS_ROLE = "1";
	/** 数据权限过滤 */
	public static final String SQL_FILTER = "sql_filter";
	/**
	 * 流程会签集合名称
	 */
	public static final String ACT_MUIT_LIST_NAME = "users";
	/**
	 * 流程会签变量名称
	 */
	public static final String ACT_MUIT_VAR_NAME = "user";
	/**
	 * 删除标记（0：正常；1：删除 ）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String FILE_SERVICE = "http://192.168.10.192:9999/";
	
	/** 缓存命名空间 */
	public static final String SYSTEM_CACHE_NAMESPACE = "s:jsy:";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = SYSTEM_CACHE_NAMESPACE + "ALLUSER_NUMBER";
	/**
	 * 合同类型编码
	 */
	public static final Map<String, String> conCodeMap = Maps.newHashMap();
	/**
	 * 劳务包合同编码
	 */
	public static final String LS_CONCODE = "H030201";
	
	/**
	 * 公司项目角色类型
	 */
	public static final String COMPANY_ROLE_TYPE = "companyRoleType";
	
	/**
	 * 公司项目
	 */
	public static final String PRO_ID = "proId";
	
	static {
		/**
		 * 钢材
		 */
		conCodeMap.put("gc", "H03020201");
		/**
		 *红砖 
		 */
		conCodeMap.put("hz", "H03020202");
		/**
		 * 加气砼砌块
		 */
		conCodeMap.put("jqbqk", "H03020203");
		/**
		 * 砂子
		 */
		conCodeMap.put("sz", "H03020204");
		/**
		 * 水泥
		 */
		conCodeMap.put("sn", "H03020205");
		/**
		 * 石灰
		 */
		conCodeMap.put("sh", "H03020206");
		/**
		 * 模板
		 */
		conCodeMap.put("mb", "H03020207");
		/**
		 * 混凝土
		 */
		conCodeMap.put("hnt", "H03020208");
	}
	
	/**
	 * 分页条数
	 */
	public static final int pageSize = 10;
	
	public static String getPicturePath() {
		String picture_path = "";
		EPlatform os = OSInfo.getOSName();
		if (os.toString().equals("Linux")) {
			picture_path = "/opt/dhjs/service";
		} else if (os.toString().equals("Windows")) {
			picture_path = "D:\\dhjs\\pic";
		}
		return picture_path;
	}
	/**
	 * 
	 * @ClassName: AdminType   
	 * @Description: 管理员类型
	 * @author: liutao
	 * @date: 2018年4月28日 下午2:10:38
	 */
	public enum AdminType {
		/**
		 * 非管理员
		 */
		NONAMIN("0"), 
		/**
		 * 超级管理员
		 */
		SUPERAMIN("1");
		private String value;
		AdminType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 
	 * @ClassName: UserType   
	 * @Description: 用户类型
	 * @author: liutao
	 * @date: 2018年4月28日 下午4:43:12
	 */
	public enum UserType {
		/**
		 * 公司员工
		 */
		EMPLOYEE("1"), 
		/**
		 * 劳务工
		 */
		WORKER("2");
		private String value;
		UserType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 菜单类型
	 */
	public enum MenuType {
		/**
		 * 目录
		 */
		CATALOG(0),
		/**
		 * 菜单
		 */
		MENU(1),
		/**
		 * 按钮
		 */
		BUTTON(2);
		private int value;
		MenuType(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	/**
	 * 定时任务状态
	 */
	public enum ScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(0),
		/**
		 * 暂停
		 */
		PAUSE(1);
		private int value;
		ScheduleStatus(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	/**
	 * 是否类型
	 */
	public enum YESNO {
		/**
		 * 是
		 */
		YES("0"),
		/**
		 * 否
		 */
		NO("1");
		private String value;
		private YESNO(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 系统用户状态
	 */
	public enum ABLE_STATUS {
		/**
		 * 正常
		 */
		YES("0"),
		/**
		 * 禁用
		 */
		NO("-1");
		private String value;
		ABLE_STATUS(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 审批节点行为
	 */
	public enum ActAction {
		/**
		 * 审批
		 */
		APPROVE("1"),
		/**
		 * 会签
		 */
		MULIT("2");
		private String value;
		private ActAction(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 流程状态
	 */
	public enum ActStauts {
		/**
		 * 草稿
		 */
		DRAFT("1"),
		/**
		 * 审批中
		 */
		APPROVAL("2"),
		/**
		 * 结束
		 */
		END("3");
		private String value;
		private ActStauts(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 流程任务审批结果
	 */
	public enum ActTaskResult {
		/**
		 * 同意
		 */
		AGREE("1"),
		/**
		 * 反对
		 */
		NO_AGREE("2"),
		/**
		 * 弃权
		 */
		ABSTAINED("3"),
		/**
		 * 驳回
		 */
		TURN_DOWN("4"),
		/**
		 * 转办
		 */
		TURN_DO("5");
		private String value;
		private ActTaskResult(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 整个流程的审批结果
	 */
	public enum ActResult {
		/**
		 * 同意
		 */
		AGREE("1"),
		/**
		 * 不同意
		 */
		NO_AGREE("2"),
		/**
		 * 审批中
		 */
		DISAGREE("3");
		private String value;
		private ActResult(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 流程办理任务，和查询流程审批信息控件显示
	 */
	public enum ActFlowDoView {
		/**
		 * 查看审批过程
		 */
		SHOW_FLOW("1"),
		/**
		 * 办理任务
		 */
		DO_TASK("2");
		private String value;
		private ActFlowDoView(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	public enum BsCodeType{
		GS("GS"),
		XM("XM"),
		LD("LD"),
		JGXS("JGXS"),
		FB("FB"),
		ZFB("ZFB"),
		FX("FX"),
		HYGH("HYGH");
		
		public String value;
		private BsCodeType(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
		
		public static BsCodeType getByValue(String value) {
			for (BsCodeType codeType : BsCodeType.values()) {
				if (Objects.equal(value, codeType.getValue())) {
					return codeType;
				}
			}
			return null;
		}
	}
	
	/***
	 * 模块
	 * @author sss
	 *
	 */
	public enum modules{
		/**
		 * 采购计划
		 */
		PURCHASE_PLAN("PURCHASE_PLAN"),
		/**
		 * 采购计划汇总
		 */
		PURCHASE_PLAN_SUM("PURCHASE_PLAN_SUM"),
		/**
		 * 入库
		 */
		PURCHASE_STOCK_IN("PURCHASE_STOCK_IN"),
		
		/**
		 * 订单
		 */
		PURCHASE_ORDER("PURCHASE_ORDER"),
		
		/**
		 * 劳务分包
		 */
		LS_CONTRACT_LABOR("LS_CONTRACT_LABOR"),
		
		/**
		 * 物资采购合同
		 */
		PURCHASE_CONTRACT("PURCHASE_CONTRACT");
		

		
		public String value;
		private modules(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 公司项目中角色类型
	 */
	public enum CompanyProRole {  
		/**
		 * 建设方
		 */
		CONSTRUCTION("constructionId", "1"), 
		/**
		 * 总包方
		 */
		BENERALCONTRACTOR("beneralcontractorId", "2"), 
		/**
		 * 分包方
		 */
		LABOR("laborId", "3"), 
		/**
		 * 监理方
		 */
		SUPERVISOR("supervisorId", "4"), 
		/**
		 * 设计方
		 */
		DESIGNER("designerId", "5"),
		/**
		 * 其它
		 */
		OTHER("other", "6");  
		
	    // 成员变量  
	    private String name;  
	    
	    private String type;  
	    // 构造方法  
	    private CompanyProRole(String name, String type) {  
	        this.name = name;  
	        this.type = type;  
	    }  
	    // 普通方法  
	    public static String getName(String type) {  
	        for (CompanyProRole c : CompanyProRole.values()) {  
	            if (c.getType().equals(type)) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public String getType() {  
	        return type;  
	    }  
	    public void setType(String type) {  
	        this.type = type;  
	    }  
	}
}
