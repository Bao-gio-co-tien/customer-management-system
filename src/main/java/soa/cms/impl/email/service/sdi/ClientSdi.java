package soa.cms.impl.email.service.sdi;

import lombok.Data;
import lombok.Getter;

@Data
public class ClientSdi {
    private String name;
    private String username;
    @Getter
    private String email;

}
