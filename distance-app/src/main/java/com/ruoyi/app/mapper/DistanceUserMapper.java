package com.ruoyi.app.mapper;

import java.util.List;

import com.ruoyi.app.entity.domain.DistanceUser;

/**
 * 用户信息Mapper接口
 *
 * @author å¼ å²©
 * @date 2024-06-25
 */
public interface DistanceUserMapper {
    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public DistanceUser selectDistanceUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     *
     * @param distanceUser 用户信息
     * @return 用户信息集合
     */
    public List<DistanceUser> selectDistanceUserList(DistanceUser distanceUser);

    /**
     * 新增用户信息
     *
     * @param distanceUser 用户信息
     * @return 结果
     */
    public int insertDistanceUser(DistanceUser distanceUser);

    /**
     * 修改用户信息
     *
     * @param distanceUser 用户信息
     * @return 结果
     */
    public int updateDistanceUser(DistanceUser distanceUser);

    /**
     * 删除用户信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteDistanceUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDistanceUserByUserIds(Long[] userIds);

    public DistanceUser selectUserByEmail(String email);
}
