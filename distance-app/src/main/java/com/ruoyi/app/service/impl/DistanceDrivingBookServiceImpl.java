package com.ruoyi.app.service.impl;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.app.entity.domain.DistanceDrivingBook;
import com.ruoyi.app.entity.dto.DrivingBookDTO;
import com.ruoyi.app.entity.dto.DrivingBookRequestDTO;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.DistanceDrivingBookMapper;

import com.ruoyi.app.service.IDistanceDrivingBookService;

/**
 * 驾考题目Service业务层处理
 *
 * @author å¼ å²©
 * @date 2024-06-29
 */
@Service
public class DistanceDrivingBookServiceImpl implements IDistanceDrivingBookService
{
    @Autowired
    private DistanceDrivingBookMapper distanceDrivingBookMapper;

    /**
     * 查询驾考题目
     *
     * @param id 驾考题目主键
     * @return 驾考题目
     */
    @Override
    public DistanceDrivingBook selectDistanceDrivingBookById(Long id)
    {
        return distanceDrivingBookMapper.selectDistanceDrivingBookById(id);
    }

    /**
     * 查询驾考题目列表
     *
     * @param distanceDrivingBook 驾考题目
     * @return 驾考题目
     */
    @Override
    public List<DistanceDrivingBook> selectDistanceDrivingBookList(DistanceDrivingBook distanceDrivingBook)
    {
        return distanceDrivingBookMapper.selectDistanceDrivingBookList(distanceDrivingBook);
    }

    /**
     * 新增驾考题目
     *
     * @param distanceDrivingBook 驾考题目
     * @return 结果
     */
    @Override
    public int insertDistanceDrivingBook(DrivingBookRequestDTO drivingBookRequestDTO)
    {


        try {

            // 打印列表中的所有对象
            for (DrivingBookDTO dto : drivingBookRequestDTO.getContent()) {

                Long questionType;
                //根据item3 & item4 是否为空判断是否是多选
                if ((dto.getItem3() == null || dto.getItem3().equals("")) && (dto.getItem4() == null || dto.getItem4().equals(""))) {
                    questionType = 2L;
                    //根据answer字符数量判断多选/单选
                } else if (dto.getAnswer().length() == 1) {
                    questionType = 0L;
                } else {
                    questionType = 1L;
                }

                DistanceDrivingBook distanceDrivingBookItem = new DistanceDrivingBook();
                distanceDrivingBookItem.setCreateTime(DateUtils.getNowDate());
                distanceDrivingBookItem.setSubject(drivingBookRequestDTO.getSubject());
                distanceDrivingBookItem.setModel(drivingBookRequestDTO.getModel());
                distanceDrivingBookItem.setContent(dto.toString());
                distanceDrivingBookItem.setQuestionType(questionType);
                distanceDrivingBookItem.setItem1(dto.getItem1());
                distanceDrivingBookItem.setItem2(dto.getItem2());
                distanceDrivingBookItem.setItem3(dto.getItem3());
                distanceDrivingBookItem.setItem4(dto.getItem4());
                distanceDrivingBookItem.setAnswer(dto.getAnswer());
                distanceDrivingBookItem.setQuestion(dto.getQuestion());
                distanceDrivingBookItem.setUrl(dto.getUrl());
                distanceDrivingBookItem.setExplains(dto.getExplains());
                distanceDrivingBookMapper.insertDistanceDrivingBook(distanceDrivingBookItem);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 修改驾考题目
     *
     * @param distanceDrivingBook 驾考题目
     * @return 结果
     */
    @Override
    public int updateDistanceDrivingBook(DistanceDrivingBook distanceDrivingBook)
    {
        distanceDrivingBook.setUpdateTime(DateUtils.getNowDate());
        return distanceDrivingBookMapper.updateDistanceDrivingBook(distanceDrivingBook);
    }

    /**
     * 批量删除驾考题目
     *
     * @param ids 需要删除的驾考题目主键
     * @return 结果
     */
    @Override
    public int deleteDistanceDrivingBookByIds(Long[] ids)
    {
        return distanceDrivingBookMapper.deleteDistanceDrivingBookByIds(ids);
    }

    /**
     * 删除驾考题目信息
     *
     * @param id 驾考题目主键
     * @return 结果
     */
    @Override
    public int deleteDistanceDrivingBookById(Long id)
    {
        return distanceDrivingBookMapper.deleteDistanceDrivingBookById(id);
    }
}
