package com.ruoyi.app.mapper;

import com.ruoyi.app.entity.domain.DistanceMessage;

import java.util.List;


/**
 * 消息内容Mapper接口
 * 
 * @author å¼ å²©
 * @date 2024-07-02
 */
public interface DistanceMessageMapper 
{
    /**
     * 查询消息内容
     * 
     * @param id 消息内容主键
     * @return 消息内容
     */
    public DistanceMessage selectDistanceMessageById(Long id);

    /**
     * 查询消息内容列表
     * 
     * @param distanceMessage 消息内容
     * @return 消息内容集合
     */
    public List<DistanceMessage> selectDistanceMessageList(DistanceMessage distanceMessage);

    /**
     * 新增消息内容
     * 
     * @param distanceMessage 消息内容
     * @return 结果
     */
    public int insertDistanceMessage(DistanceMessage distanceMessage);

    /**
     * 修改消息内容
     * 
     * @param distanceMessage 消息内容
     * @return 结果
     */
    public int updateDistanceMessage(DistanceMessage distanceMessage);

    /**
     * 删除消息内容
     * 
     * @param id 消息内容主键
     * @return 结果
     */
    public int deleteDistanceMessageById(Long id);

    /**
     * 批量删除消息内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDistanceMessageByIds(Long[] ids);
}
