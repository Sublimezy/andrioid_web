package com.ruoyi.app.entity.emum;

/**
 * 登录类型
 */
public enum UserLoginModeEnum {

    PASSWORD("密码登录"),
    CAPTCHA("验证码登录");

    private final String description;

    UserLoginModeEnum(String description) {
        this.description = description;

    }

    public String description() {
        return description;
    }

}
