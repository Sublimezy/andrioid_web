package com.ruoyi.app.entity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驾考题目对象 distance_driving_book

 */
public class DistanceDrivingBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 科目类型 */
    @Excel(name = "科目类型")
    private Long subject;

    /** 驾照类型 */
    @Excel(name = "驾照类型")
    private String model;

    /** 题目类型 */
    @Excel(name = "题目类型")
    private Long questionType;

    /** 考题内容 */
    @Excel(name = "考题内容")
    private String content;

    /** 选项一 */
    @Excel(name = "选项一")
    private String item1;

    /** 选项二 */
    @Excel(name = "选项二")
    private String item2;

    /** 选项三 */
    @Excel(name = "选项三")
    private String item3;

    /** 选项四 */
    @Excel(name = "选项四")
    private String item4;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 问题 */
    @Excel(name = "问题")
    private String question;

    /** 解释 */
    @Excel(name = "解释")
    private String explains;

    /** 资源地址 */
    @Excel(name = "资源地址")
    private String url;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSubject(Long subject)
    {
        this.subject = subject;
    }

    public Long getSubject()
    {
        return subject;
    }
    public void setModel(String model)
    {
        this.model = model;
    }

    public String getModel()
    {
        return model;
    }
    public void setQuestionType(Long questionType)
    {
        this.questionType = questionType;
    }

    public Long getQuestionType()
    {
        return questionType;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setItem1(String item1)
    {
        this.item1 = item1;
    }

    public String getItem1()
    {
        return item1;
    }
    public void setItem2(String item2)
    {
        this.item2 = item2;
    }

    public String getItem2()
    {
        return item2;
    }
    public void setItem3(String item3)
    {
        this.item3 = item3;
    }

    public String getItem3()
    {
        return item3;
    }
    public void setItem4(String item4)
    {
        this.item4 = item4;
    }

    public String getItem4()
    {
        return item4;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswer()
    {
        return answer;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getQuestion()
    {
        return question;
    }
    public void setExplains(String explains)
    {
        this.explains = explains;
    }

    public String getExplains()
    {
        return explains;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("subject", getSubject())
                .append("model", getModel())
                .append("questionType", getQuestionType())
                .append("content", getContent())
                .append("item1", getItem1())
                .append("item2", getItem2())
                .append("item3", getItem3())
                .append("item4", getItem4())
                .append("answer", getAnswer())
                .append("question", getQuestion())
                .append("explains", getExplains())
                .append("url", getUrl())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
