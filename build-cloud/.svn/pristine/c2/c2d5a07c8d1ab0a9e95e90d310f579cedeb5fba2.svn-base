package com.build.cloud.modules.bs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 整改信息表
 * </p>
 *
 * @author gongjy
 * @since 2018-07-25
 */
@TableName("bs_rectification")
public class BsRectificationEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单ID
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 整改时间
     */
    @TableField("correction_time")
    private Date correctionTime;
    /**
     * 整改完成时间
     */
    @TableField("finish_time")
    private Date finishTime;
    /**
     * 整改内容
     */
    @TableField("correction_content")
    private String correctionContent;
    /**
     * 缩略图地址
     */
    @TableField("thumbnail_imgpath")
    private String thumbnailImgpath;
    /**
     * 原型图片地址
     */
    @TableField("correction_imgpath")
    private String correctionImgpath;
    /**
     * 整改处理状态(0,未处理 1，已处理)
     */
    @TableField("deal_status")
    private String dealStatus;
    /**
     * 处理人
     */
    @TableField("deal_user_id")
    private String dealUserId;
    /**
     * 整改发起人
     */
    @TableField("deal_initiator")
    private String dealInitiator;

    @TableField(exist = false)
    private String workStatus;

    public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getCorrectionTime() {
        return correctionTime;
    }

    public void setCorrectionTime(Date correctionTime) {
        this.correctionTime = correctionTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getCorrectionContent() {
        return correctionContent;
    }

    public void setCorrectionContent(String correctionContent) {
        this.correctionContent = correctionContent;
    }

    public String getThumbnailImgpath() {
        return thumbnailImgpath;
    }

    public void setThumbnailImgpath(String thumbnailImgpath) {
        this.thumbnailImgpath = thumbnailImgpath;
    }

    public String getCorrectionImgpath() {
        return correctionImgpath;
    }

    public void setCorrectionImgpath(String correctionImgpath) {
        this.correctionImgpath = correctionImgpath;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealInitiator() {
        return dealInitiator;
    }

    public void setDealInitiator(String dealInitiator) {
        this.dealInitiator = dealInitiator;
    }


}
