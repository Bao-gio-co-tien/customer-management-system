package com.laptrinhb2a.springboot.mailsender.service.impl;

import com.laptrinhb2a.springboot.mailsender.utils.Const;
import com.laptrinhb2a.springboot.mailsender.dto.DataMailDTO;
import com.laptrinhb2a.springboot.mailsender.service.sdi.ClientSdi;
import com.laptrinhb2a.springboot.mailsender.service.MailService;
import com.laptrinhb2a.springboot.mailsender.service.ClientService;
import com.laptrinhb2a.springboot.mailsender.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            props.put("password", DataUtils.generateTempPwd(6));
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }
}
