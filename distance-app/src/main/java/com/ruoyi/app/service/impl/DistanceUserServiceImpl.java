package com.ruoyi.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.app.entity.dto.UserLoginDTO;
import com.ruoyi.app.entity.vo.UserLoginSuccessVO;
import com.ruoyi.app.entity.emum.UserLoginModeEnum;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.UserLogin;
import com.ruoyi.common.utils.uuid.IdUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.DistanceUserMapper;
import com.ruoyi.app.entity.domain.DistanceUser;
import com.ruoyi.app.service.IDistanceUserService;

import static cn.hutool.jwt.JWTUtil.createToken;

/**
 * 用户信息Service业务层处理
 *
 * @author å¼ å²©
 * @date 2024-06-25
 */
@Service
public class DistanceUserServiceImpl implements IDistanceUserService {
    @Autowired
    private DistanceUserMapper distanceUserMapper;

    @Value("${token.secret}")
    private String secret;


    @Autowired
    private RedisCache redisService;

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public DistanceUser selectDistanceUserByUserId(Long userId) {
        return distanceUserMapper.selectDistanceUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     *
     * @param distanceUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<DistanceUser> selectDistanceUserList(DistanceUser distanceUser) {
        return distanceUserMapper.selectDistanceUserList(distanceUser);
    }

    /**
     * 新增用户信息
     *
     * @param distanceUser 用户信息
     * @return 结果
     */
    @Override
    public int insertDistanceUser(DistanceUser distanceUser) {
        distanceUser.setCreateTime(DateUtils.getNowDate());
        return distanceUserMapper.insertDistanceUser(distanceUser);
    }

    /**
     * 修改用户信息
     *
     * @param distanceUser 用户信息
     * @return 结果
     */
    @Override
    public int updateDistanceUser(DistanceUser distanceUser) {
        distanceUser.setUpdateTime(DateUtils.getNowDate());
        return distanceUserMapper.updateDistanceUser(distanceUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteDistanceUserByUserIds(Long[] userIds) {
        return distanceUserMapper.deleteDistanceUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteDistanceUserByUserId(Long userId) {
        return distanceUserMapper.deleteDistanceUserByUserId(userId);
    }

    @Override
    public UserLoginSuccessVO login(UserLoginDTO userLoginDTO) {

        DistanceUser distanceUser = null;
        String tokenGEN = null;

        UserLoginSuccessVO userLoginSuccessVO = null;
        switch (UserLoginModeEnum.valueOf(userLoginDTO.getLoginType())) {

            case PASSWORD:

                String userName = userLoginDTO.getUserName();

                String password = userLoginDTO.getPassword();

                // 用户名或密码为空 错误
                if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userName)) {
                    throw new UserNotExistsException();
                }
                if (StringUtils.isEmpty(password) || StringUtils.isEmpty(password)) {
                    throw new UserNotExistsException();
                }
                break;
            case CAPTCHA:

                String email = userLoginDTO.getEmail();

                String captchaUser = userLoginDTO.getCaptcha();

                // 用户名或密码为空 错误
                if (StringUtils.isEmpty(email) || StringUtils.isEmpty(email)) {
                    throw new UserNotExistsException();
                }
                if (StringUtils.isEmpty(captchaUser) || StringUtils.isEmpty(captchaUser)) {
                    throw new UserNotExistsException();
                }


                String verifyKey = CacheConstants.CAPTCHA_EMAIL_CODE_KEY + email;

                String captchaRedis = redisService.getCacheObject(verifyKey);
                if (!captchaUser.equals(captchaRedis)) {
                    throw new UserPasswordNotMatchException();
                }

                distanceUser = distanceUserMapper.selectUserByEmail(email);

                if (distanceUser == null) {
                    String account = UserLogin.generateDefaultAccount();
                    DistanceUser distanceUserRegister = new DistanceUser(account, "默认昵称", email, "", SecurityUtils.encryptPassword(account));
                    //进行注册
                    System.out.println("请求到达");
                    int i = distanceUserMapper.insertDistanceUser(distanceUserRegister);
                    distanceUser = distanceUserMapper.selectDistanceUserByUserId((long) i);
                }


                String token = IdUtils.fastUUID();


                Map<String, Object> claims = new HashMap<>();
                claims.put(Constants.LOGIN_USER_KEY, token);

                tokenGEN = Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, secret).compact();

                userLoginSuccessVO = new UserLoginSuccessVO(
                        distanceUser,
                        tokenGEN
                );
        }
        return userLoginSuccessVO;

    }
}
