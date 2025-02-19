package com.laptrinhb2a.springboot.mailsender.service;

import com.laptrinhb2a.springboot.mailsender.service.sdi.ClientSdi;

public interface ClientService {
    Boolean create(ClientSdi sdi);
}
