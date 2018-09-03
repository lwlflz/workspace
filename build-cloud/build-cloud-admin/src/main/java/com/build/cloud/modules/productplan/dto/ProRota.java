package com.build.cloud.modules.productplan.dto;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-05-03
 */
@TableName("pro_rota")
public class ProRota extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
	@TableField("contract_id")
	private String contractId;
    /**
     * 项目id
     */
	@TableField("project_id")
	private String projectId;
    /**
     * 班组id
     */
	@TableField("team_id")
	private String teamId;
    /**
     * 状态
     */
	@TableField("rota_staus")
	private String rotaStaus;
    /**
     * 现场负责人
     */
	@TableField("duty_by")
	private String dutyBy;
    /**
     * 班组长
     */
	private String teamer;
	
	@TableField(exist = false)
	List<ProRotaWorker> workerList;
	
	@TableField(exist = false)
	private String teamName;
	
	@TableField(exist = false)
	private String conName;
	
	@TableField(exist = false)
	private String leaderName;
	
	@TableField(exist = false)
	private String dutyDname;
	
	@TableField(exist = false)
	private String teamTypename;
	
	@TableField(exist = false)
	private String teamType;
	
	@TableField(exist = false)
	private Integer workCount;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getRotaStaus() {
		return rotaStaus;
	}

	public void setRotaStaus(String rotaStaus) {
		this.rotaStaus = rotaStaus;
	}

	public String getDutyBy() {
		return dutyBy;
	}

	public void setDutyBy(String dutyBy) {
		this.dutyBy = dutyBy;
	}

	public String getTeamer() {
		return teamer;
	}

	public void setTeamer(String teamer) {
		this.teamer = teamer;
	}

	public List<ProRotaWorker> getWorkerList() {
		return workerList;
	}

	public void setWorkerList(List<ProRotaWorker> workerList) {
		this.workerList = workerList;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getDutyDname() {
		return dutyDname;
	}

	public void setDutyDname(String dutyDname) {
		this.dutyDname = dutyDname;
	}

	public String getTeamTypename() {
		return teamTypename;
	}

	public void setTeamTypename(String teamTypename) {
		this.teamTypename = teamTypename;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public Integer getWorkCount() {
		return workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}
	
}
