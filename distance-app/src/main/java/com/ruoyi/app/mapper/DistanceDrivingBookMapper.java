package com.ruoyi.app.mapper;



import com.ruoyi.app.entity.domain.DistanceDrivingBook;

import java.util.List;

/**
 * 驾考题目Mapper接口
 *
 * @author å¼ å²©
 * @date 2024-06-29
 */
public interface DistanceDrivingBookMapper
{
    /**
     * 查询驾考题目
     *
     * @param id 驾考题目主键
     * @return 驾考题目
     */
    public DistanceDrivingBook selectDistanceDrivingBookById(Long id);

    /**
     * 查询驾考题目列表
     *
     * @param distanceDrivingBook 驾考题目
     * @return 驾考题目集合
     */
    public List<DistanceDrivingBook> selectDistanceDrivingBookList(DistanceDrivingBook distanceDrivingBook);

    /**
     * 新增驾考题目
     *
     * @param distanceDrivingBook 驾考题目
     * @return 结果
     */
    public int insertDistanceDrivingBook(DistanceDrivingBook distanceDrivingBook);

    /**
     * 修改驾考题目
     *
     * @param distanceDrivingBook 驾考题目
     * @return 结果
     */
    public int updateDistanceDrivingBook(DistanceDrivingBook distanceDrivingBook);

    /**
     * 删除驾考题目
     *
     * @param id 驾考题目主键
     * @return 结果
     */
    public int deleteDistanceDrivingBookById(Long id);

    /**
     * 批量删除驾考题目
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDistanceDrivingBookByIds(Long[] ids);
}
