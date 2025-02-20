package soa.group.project.cms.impl.email.service.impl;

import soa.group.project.cms.impl.email.dto.DataMailDTO;
import soa.group.project.cms.impl.email.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(DataMailDTO dataMail, String templateName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            Context context = new Context();
            context.setVariables(dataMail.getProps());

            String html = templateEngine.process(templateName, context);

            helper.setTo(dataMail.getTo());
            helper.setSubject(dataMail.getSubject());
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}", dataMail.getTo(), e);
            try {
                throw e;
            } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
