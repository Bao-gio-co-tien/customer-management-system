package soa.cms.impl.email.service.impl;

import soa.cms.impl.email.dto.DataMailDTO;
import soa.cms.impl.email.service.sdi.ClientSdi;
import soa.cms.impl.email.utils.Const;
import soa.cms.impl.email.service.MailService;
import soa.cms.impl.email.service.ClientService;
import soa.cms.impl.email.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        } catch (javax.mail.MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }
}
