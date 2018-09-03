package com.build.cloud.modules.productplan.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 消息子表
 * </p>
 *
 * @author gongjy
 * @since 2018-05-28
 */
@TableName("pro_message_child")
public class ProMessageChildEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 消息主键
     */
    @TableField("msg_id")
    private String msgId;
    /**
     * 接收人id(用户id)
     */
    @TableField("receive_id")
    private String receiveId;
    /**
     * 消息状态(0未读 1已读)
     */
    @TableField("msg_status")
    private String msgStatus;
    /**
     * 处理状态0未处理 1已处理
     */
    @TableField("handle_status")
    private String handleStatus;

	public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    @Override
    public String toString() {
        return "ProMessageChild{" +
        ", msgId=" + msgId +
        ", receiveId=" + receiveId +
        ", msgStatus=" + msgStatus +
        ", handleStatus=" + handleStatus +
        "}";
    }
}
