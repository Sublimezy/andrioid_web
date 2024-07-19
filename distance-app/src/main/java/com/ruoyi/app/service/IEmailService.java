package com.ruoyi.app.service;


import com.ruoyi.app.entity.dto.EmailDTO;

import javax.mail.MessagingException;

public interface IEmailService {
    void sendEmail(EmailDTO emailDTO) throws MessagingException;


}
