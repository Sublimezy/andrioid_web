package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.entity.domain.DistanceUser;
import com.ruoyi.app.entity.dto.UserLoginDTO;
import com.ruoyi.app.entity.vo.UserLoginSuccessVO;

/**
 * 用户信息Service接口
 * 
 * @author å¼ å²©
 * @date 2024-06-25
 */
public interface IDistanceUserService 
{
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
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteDistanceUserByUserIds(Long[] userIds);

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteDistanceUserByUserId(Long userId);

    UserLoginSuccessVO login(UserLoginDTO userLoginDTO);
}
