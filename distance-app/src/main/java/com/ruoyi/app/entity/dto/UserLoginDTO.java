package com.ruoyi.app.entity.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    /**
     * 用户账号
     */

    private String userName;


    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 登录方式
     */
    private String loginType;
    /**
     * 密码
     */
    private String password;


    /**
     * 验证码
     */
    private String captcha;

    public void setPropertiesFromMap(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "userName":
                    this.userName = entry.getValue();
                    break;
                case "email":
                    this.email = entry.getValue();
                    break;
                case "phone":
                    this.phone = entry.getValue();
                    break;
                case "loginType":
                    this.loginType = entry.getValue();
                    break;
                case "password":
                    this.password = entry.getValue();
                    break;
                case "captcha":
                    this.captcha = entry.getValue();
                    break;
                default:
                    // Handle unknown keys if necessary
                    break;
            }
        }
    }



}
