package com.ruoyi.app.config;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class MailConfig {
    
    @Value("${spring.mail.host}")
    private String host;
    
    @Value("${spring.mail.username}")
    private String username;
    
    @Value("${spring.mail.password}")
    private String password;
    
    @Value("${spring.mail.default-encoding}")
    private String defaultEncoding;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    @Override
    public String toString() {
        return "MailConfig{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", defaultEncoding='" + defaultEncoding + '\'' +
                '}';
    }

    public MailConfig() {
    }

    public MailConfig(String host, String username, String password, String defaultEncoding) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.defaultEncoding = defaultEncoding;
    }
}
