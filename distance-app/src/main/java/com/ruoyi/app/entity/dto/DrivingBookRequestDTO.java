package com.ruoyi.app.entity.dto;

import com.ruoyi.common.annotation.Excel;

import java.util.List;


public class DrivingBookRequestDTO {
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
    private List<DrivingBookDTO> content;


    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Long questionType) {
        this.questionType = questionType;
    }

    public List<DrivingBookDTO> getContent() {
        return content;
    }

    public void setContent(List<DrivingBookDTO> content) {
        this.content = content;
    }

    public DrivingBookRequestDTO(Long subject, String model, Long questionType, List<DrivingBookDTO> content) {
        this.subject = subject;
        this.model = model;
        this.questionType = questionType;
        this.content = content;
    }

    public DrivingBookRequestDTO() {
    }
}
