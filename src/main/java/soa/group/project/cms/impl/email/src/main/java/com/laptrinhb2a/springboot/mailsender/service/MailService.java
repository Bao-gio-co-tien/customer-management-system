package com.laptrinhb2a.springboot.mailsender.service;

import com.laptrinhb2a.springboot.mailsender.dto.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
