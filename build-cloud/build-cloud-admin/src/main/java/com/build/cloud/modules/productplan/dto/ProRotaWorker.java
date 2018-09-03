package com.build.cloud.modules.productplan.dto;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 * </p>
 * @author liangsen
 * @since 2018-05-03
 */
@TableName("pro_rota_worker")
public class ProRotaWorker extends Model<ProRotaWorker> {
	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 花名册id
	 */
	@TableField("rota_id")
	private String rotaId;
	/**
	 * 工人id
	 */
	@TableField("worker_id")
	private String workerId;
	/**
	 * 员工ID EMPLID，与考勤机编号对应
	 */
	@TableField("ng_id")
	private String ngId;
	/**
	 * 姓名
	 */
	@TableField(exist = false)
	private String name;
	/**
	 * 身份证
	 */
	@TableField(exist = false)
	private String idcard;
	/**
	 * 工种(1、钢筋工 2、混凝土工 3、架子工 4、木工 5、装饰工 6、砌体工 7、防水工 8、水电工 9、抹灰工)
	 */
	@TableField(exist = false)
	private String workType;
	/**
	 * 年龄
	 */
	@TableField(exist = false)
	private Integer age;
	/**
	 * 联系电话
	 */
	@TableField(exist = false)
	private String phone;
	/**
	 * 技能等级(1、大工 2、小工)
	 */
	@TableField(exist = false)
	private String skillLevel;
	@TableField(exist = false)
	private String skillLevelname;
	@TableField(exist = false)
	private String workTypename;
	@TableField(exist = false)
	private String workerIds;
	@TableField(exist = false)
	private String projectId;
	@TableField(exist = false)
	private Integer devCode;
	/**
	 * 进场时间
	 */
	@TableField("in_date")
	private Date inDate;
	/**
	 * 退场时间
	 */
	@TableField("out_date")
	private Date outDate;
	/**
	 * 进出状态(0进场，1退场)
	 */
	@TableField("state")
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Integer getDevCode() {
		return devCode;
	}
	public void setDevCode(Integer devCode) {
		this.devCode = devCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRotaId() {
		return rotaId;
	}
	public void setRotaId(String rotaId) {
		this.rotaId = rotaId;
	}
	public String getWorkerId() {
		return workerId;
	}
	public String getNgId() {
		return ngId;
	}
	public void setNgId(String ngId) {
		this.ngId = ngId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	public String getSkillLevelname() {
		return skillLevelname;
	}
	public void setSkillLevelname(String skillLevelname) {
		this.skillLevelname = skillLevelname;
	}
	public String getWorkTypename() {
		return workTypename;
	}
	public void setWorkTypename(String workTypename) {
		this.workTypename = workTypename;
	}
	public String getWorkerIds() {
		return workerIds;
	}
	public void setWorkerIds(String workerIds) {
		this.workerIds = workerIds;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
