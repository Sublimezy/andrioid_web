package com.ruoyi.app.entity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息内容对象 distance_message
 * 
 * @author å¼ å²©
 * @date 2024-07-02
 */
public class DistanceMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片 */
    @Excel(name = "图片")
    private String messageImage;

    /** 消息标题 */
    @Excel(name = "消息标题")
    private String messageTextTitle;

    /** 消息描述 */
    @Excel(name = "消息描述")
    private String messageTextDescribe;

    /** 消息创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "消息创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date messageTextTime;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messageText;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMessageImage(String messageImage) 
    {
        this.messageImage = messageImage;
    }

    public String getMessageImage() 
    {
        return messageImage;
    }
    public void setMessageTextTitle(String messageTextTitle) 
    {
        this.messageTextTitle = messageTextTitle;
    }

    public String getMessageTextTitle() 
    {
        return messageTextTitle;
    }
    public void setMessageTextDescribe(String messageTextDescribe) 
    {
        this.messageTextDescribe = messageTextDescribe;
    }

    public String getMessageTextDescribe() 
    {
        return messageTextDescribe;
    }
    public void setMessageTextTime(Date messageTextTime) 
    {
        this.messageTextTime = messageTextTime;
    }

    public Date getMessageTextTime() 
    {
        return messageTextTime;
    }
    public void setMessageText(String messageText) 
    {
        this.messageText = messageText;
    }

    public String getMessageText() 
    {
        return messageText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("messageImage", getMessageImage())
            .append("messageTextTitle", getMessageTextTitle())
            .append("messageTextDescribe", getMessageTextDescribe())
            .append("messageTextTime", getMessageTextTime())
            .append("messageText", getMessageText())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
