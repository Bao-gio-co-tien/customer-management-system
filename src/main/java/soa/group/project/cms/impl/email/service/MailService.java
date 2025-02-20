package soa.group.project.cms.impl.email.service;

import soa.group.project.cms.impl.email.dto.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
