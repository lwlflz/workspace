package com.build.cloud.modules.productplan.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;


/**
 * <p>
 * 消息主表
 * </p>
 *
 * @author gongjy
 * @since 2018-05-28
 */
@TableName("pro_message")
public class ProMessageEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @TableField("msg_name")
    private String msgName;
    /**
     * 内容
     */
    @TableField("msg_content")
    private String msgContent;
    /**
     * 消息类型(1任务 2通知,其它以后扩展)
     */
    @TableField("msg_type")
    private String msgType;
    /**
     * 消息参数
     */
    @TableField("msg_params")
    private String msgParams;
    /**
     * 创建人id(用户id)
     */
    @TableField("send_id")
    private String sendId;

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(String msgParams) {
        this.msgParams = msgParams;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    @Override
    public String toString() {
        return "ProMessage{" +
        ", msgName=" + msgName +
        ", msgContent=" + msgContent +
        ", msgType=" + msgType +
        ", msgParams=" + msgParams +
        ", sendId=" + sendId +
        "}";
    }
}
