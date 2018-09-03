package com.build.cloud.modules.psn.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.build.cloud.common.validator.group.UpdateGroup;

import java.io.Serializable;

/**
 * <p>
 * 规则设置
 * </p>
 *
 * @author wangjun
 * @since 2018-08-09
 */
@TableName("psn_control_rules")
public class PsnControlRulesEntity extends Model<PsnControlRulesEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 公司id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 童工年龄
     */
    @TableField("child_age")
    @Range(max=18,min=0,groups = {UpdateGroup.class} ,message="童工年龄要小于18")
    private Integer childAge;
    /**
     * 童工年龄限制
     */
    @TableField("is_child_age")
    private Integer isChildAge;
    /**
     * 男性退休年龄
     */
    @TableField("man_retire_age")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="请输入三位数以内年龄女性退休年龄值")
    private Integer manRetireAge;
    /**
     * 男性退休年龄限制
     */
    @TableField("is_man_age")
    private Integer isManAge;
    /**
     * 女性退休年龄
     */
    @TableField("feman_retire_age")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="请输入三位数以内年龄女性退休年龄值")
    private Integer femanRetireAge;
    /**
     * 女性退休年龄限制
     */
    @TableField("is_feman_age")
    private Integer isFemanAge;
    /**
     * 证书规则校验
     */
    @TableField("is_check_rules")
    private Integer isCheckRules;
    /**
     * 是否启用工人照片
     */
    @TableField("is_photo")
    private Integer isPhoto;
    /**
     * 未进行安全教育是否运行进场
     */
    @TableField("is_educat")
    private Integer isEducat;
    /**
     * 安全教育天数
     */
    @TableField("educat_date")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="安全教育天数请输入三位数")
    private Integer educatDate;
    /**
     * 安全教育天数启用状态
     */
    @TableField("is_educat_date")
    private Integer isEducatDate;
    /**
     * 人员长时间不考勤不失效
     */
    @TableField("is_attend")
    private Integer isAttend;
    /**
     * 考勤天数
     */
    @TableField("attend_date")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="考勤天数请输入三位数")
    private Integer attendDate;
    /**
     * 考勤失效是否启用
     */
    @TableField("is_attend_date")
    private Integer isAttendDate;
    /**
     * 班组出勤率
     */
    @TableField("team_attend")
    @Range(max=100,min=0,groups = {UpdateGroup.class} ,message="班组出勤率要在0-100之间")
    private Integer teamAttend;
    /**
     * 班组出勤率预警
     */
    @TableField("is_team_attend")
    private Integer isTeamAttend;
    /**
     * 公司出勤率
     */
    @TableField("company_attend")
    @Range(max=100,min=0,groups = {UpdateGroup.class} ,message="公司出勤率要在0-100之间")
    private Integer companyAttend;
    /**
     * 公司出勤率预警
     */
    @TableField("is_company_attend")
    private Integer isCompanyAttend;
    /**
     * 工人连续在场时间
     */
    @TableField("continue_hours")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="工人连续在场时间请输入三位数")
    private Integer continueHours;
    /**
     * 工人连续在场时间预警
     */
    @TableField("is_continue_hours")
    private Integer isContinueHours;
    /**
     * 夜间超时时间
     */
    @TableField("night_timeout")
    
    private String nightTimeout;
    /**
     * 夜间超时是否预警
     */
    @TableField("is_night_timeout")
    private Integer isNightTimeout;
    /**
     * 工人连续在场天数
     */
    @TableField("continue_date")
    @Range(max=999,min=0,groups = {UpdateGroup.class} ,message="工人连续在场天数请输入三位数")
    private Integer continueDate;
    /**
     * 工人在场时间
     */
    @TableField("continue_date_hours")
    @Range(max=24,min=0,groups = {UpdateGroup.class} ,message="工人连续在场时间请输入0-24之间")
    private Integer continueDateHours;
    /**
     * 工人连续在场天数与时间预警
     */
    @TableField("is_continue")
    private Integer isContinue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public Integer getIsChildAge() {
        return isChildAge;
    }

    public void setIsChildAge(Integer isChildAge) {
        this.isChildAge = isChildAge;
    }

    public Integer getManRetireAge() {
        return manRetireAge;
    }

    public void setManRetireAge(Integer manRetireAge) {
        this.manRetireAge = manRetireAge;
    }

    public Integer getIsManAge() {
        return isManAge;
    }

    public void setIsManAge(Integer isManAge) {
        this.isManAge = isManAge;
    }

    public Integer getFemanRetireAge() {
        return femanRetireAge;
    }

    public void setFemanRetireAge(Integer femanRetireAge) {
        this.femanRetireAge = femanRetireAge;
    }

    public Integer getIsFemanAge() {
        return isFemanAge;
    }

    public void setIsFemanAge(Integer isFemanAge) {
        this.isFemanAge = isFemanAge;
    }

    public Integer getIsCheckRules() {
        return isCheckRules;
    }

    public void setIsCheckRules(Integer isCheckRules) {
        this.isCheckRules = isCheckRules;
    }

    public Integer getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(Integer isPhoto) {
        this.isPhoto = isPhoto;
    }

    public Integer getIsEducat() {
        return isEducat;
    }

    public void setIsEducat(Integer isEducat) {
        this.isEducat = isEducat;
    }

    public Integer getEducatDate() {
        return educatDate;
    }

    public void setEducatDate(Integer educatDate) {
        this.educatDate = educatDate;
    }

    public Integer getIsEducatDate() {
        return isEducatDate;
    }

    public void setIsEducatDate(Integer isEducatDate) {
        this.isEducatDate = isEducatDate;
    }

    public Integer getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Integer isAttend) {
        this.isAttend = isAttend;
    }

    public Integer getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Integer attendDate) {
        this.attendDate = attendDate;
    }

    public Integer getIsAttendDate() {
        return isAttendDate;
    }

    public void setIsAttendDate(Integer isAttendDate) {
        this.isAttendDate = isAttendDate;
    }

    public Integer getTeamAttend() {
        return teamAttend;
    }

    public void setTeamAttend(Integer teamAttend) {
        this.teamAttend = teamAttend;
    }

    public Integer getIsTeamAttend() {
        return isTeamAttend;
    }

    public void setIsTeamAttend(Integer isTeamAttend) {
        this.isTeamAttend = isTeamAttend;
    }

    public Integer getCompanyAttend() {
        return companyAttend;
    }

    public void setCompanyAttend(Integer companyAttend) {
        this.companyAttend = companyAttend;
    }

    public Integer getIsCompanyAttend() {
        return isCompanyAttend;
    }

    public void setIsCompanyAttend(Integer isCompanyAttend) {
        this.isCompanyAttend = isCompanyAttend;
    }

    public Integer getContinueHours() {
        return continueHours;
    }

    public void setContinueHours(Integer continueHours) {
        this.continueHours = continueHours;
    }

    public Integer getIsContinueHours() {
        return isContinueHours;
    }

    public void setIsContinueHours(Integer isContinueHours) {
        this.isContinueHours = isContinueHours;
    }

    public String getNightTimeout() {
        return nightTimeout;
    }

    public void setNightTimeout(String nightTimeout) {
        this.nightTimeout = nightTimeout;
    }

    public Integer getIsNightTimeout() {
        return isNightTimeout;
    }

    public void setIsNightTimeout(Integer isNightTimeout) {
        this.isNightTimeout = isNightTimeout;
    }

    public Integer getContinueDate() {
        return continueDate;
    }

    public void setContinueDate(Integer continueDate) {
        this.continueDate = continueDate;
    }

    public Integer getContinueDateHours() {
        return continueDateHours;
    }

    public void setContinueDateHours(Integer continueDateHours) {
        this.continueDateHours = continueDateHours;
    }

    public Integer getIsContinue() {
        return isContinue;
    }

    public void setIsContinue(Integer isContinue) {
        this.isContinue = isContinue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PsnControlRules{" +
        "id=" + id +
        ", companyId=" + companyId +
        ", childAge=" + childAge +
        ", isChildAge=" + isChildAge +
        ", manRetireAge=" + manRetireAge +
        ", isManAge=" + isManAge +
        ", femanRetireAge=" + femanRetireAge +
        ", isFemanAge=" + isFemanAge +
        ", isCheckRules=" + isCheckRules +
        ", isPhoto=" + isPhoto +
        ", isEducat=" + isEducat +
        ", educatDate=" + educatDate +
        ", isEducatDate=" + isEducatDate +
        ", isAttend=" + isAttend +
        ", attendDate=" + attendDate +
        ", isAttendDate=" + isAttendDate +
        ", teamAttend=" + teamAttend +
        ", isTeamAttend=" + isTeamAttend +
        ", companyAttend=" + companyAttend +
        ", isCompanyAttend=" + isCompanyAttend +
        ", continueHours=" + continueHours +
        ", isContinueHours=" + isContinueHours +
        ", nightTimeout=" + nightTimeout +
        ", isNightTimeout=" + isNightTimeout +
        ", continueDate=" + continueDate +
        ", continueDateHours=" + continueDateHours +
        ", isContinue=" + isContinue +
        "}";
    }
}
