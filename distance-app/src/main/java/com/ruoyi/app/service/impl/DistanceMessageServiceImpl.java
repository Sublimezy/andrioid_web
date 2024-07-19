package com.ruoyi.app.service.impl;

import java.util.List;

import com.ruoyi.app.entity.domain.DistanceMessage;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.DistanceMessageMapper;

import com.ruoyi.app.service.IDistanceMessageService;

/**
 * 消息内容Service业务层处理
 * 
 * @author å¼ å²©
 * @date 2024-07-02
 */
@Service
public class DistanceMessageServiceImpl implements IDistanceMessageService 
{
    @Autowired
    private DistanceMessageMapper distanceMessageMapper;

    /**
     * 查询消息内容
     * 
     * @param id 消息内容主键
     * @return 消息内容
     */
    @Override
    public DistanceMessage selectDistanceMessageById(Long id)
    {
        return distanceMessageMapper.selectDistanceMessageById(id);
    }

    /**
     * 查询消息内容列表
     * 
     * @param distanceMessage 消息内容
     * @return 消息内容
     */
    @Override
    public List<DistanceMessage> selectDistanceMessageList(DistanceMessage distanceMessage)
    {
        return distanceMessageMapper.selectDistanceMessageList(distanceMessage);
    }

    /**
     * 新增消息内容
     * 
     * @param distanceMessage 消息内容
     * @return 结果
     */
    @Override
    public int insertDistanceMessage(DistanceMessage distanceMessage)
    {
        distanceMessage.setCreateTime(DateUtils.getNowDate());
        distanceMessage.setMessageTextTime(DateUtils.getNowDate());
        return distanceMessageMapper.insertDistanceMessage(distanceMessage);
    }

    /**
     * 修改消息内容
     * 
     * @param distanceMessage 消息内容
     * @return 结果
     */
    @Override
    public int updateDistanceMessage(DistanceMessage distanceMessage)
    {
        distanceMessage.setUpdateTime(DateUtils.getNowDate());
        return distanceMessageMapper.updateDistanceMessage(distanceMessage);
    }

    /**
     * 批量删除消息内容
     * 
     * @param ids 需要删除的消息内容主键
     * @return 结果
     */
    @Override
    public int deleteDistanceMessageByIds(Long[] ids)
    {
        return distanceMessageMapper.deleteDistanceMessageByIds(ids);
    }

    /**
     * 删除消息内容信息
     * 
     * @param id 消息内容主键
     * @return 结果
     */
    @Override
    public int deleteDistanceMessageById(Long id)
    {
        return distanceMessageMapper.deleteDistanceMessageById(id);
    }
}
