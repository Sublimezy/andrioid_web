package com.ruoyi.web.controller.app;


import com.ruoyi.app.entity.dto.EmailDTO;
import com.ruoyi.app.service.IEmailService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.Map;

import static com.ruoyi.common.core.domain.AjaxResult.error;
import static com.ruoyi.common.core.domain.AjaxResult.success;


@RestController
@RequestMapping("/app/email")
public class DistanceEmailController {


    @Autowired
    private RedisCache redisService;

    @Autowired
    private IEmailService emailService;

    //发送验证码
    @PostMapping("/sendEmail")
    public AjaxResult sendEmail(@RequestParam Map<String, String> params)  {

        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setPropertiesFromMap(params);

        try {
        /*        String verifyKey = CacheConstants.CAPTCHA_EMAIL_CODE_KEY + emailDTO.getEmail();

        String captcha = redisService.getCacheObject(verifyKey);
            if (StringUtils.isNotEmpty(captcha)) {
                return error("验证码发送频繁，请稍后再试！");
            }*/

            emailService.sendEmail(emailDTO);
            return success("成功发送邮箱验证码");
        } catch (MessagingException e) {
            e.printStackTrace();
            return error("发送失败");
        }
    }


}





