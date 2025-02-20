package soa.cms.impl.email.controller;

import soa.cms.impl.email.service.sdi.ClientSdi;
import soa.cms.impl.email.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> create(
            @RequestBody ClientSdi sdi
    ) {
        return ResponseEntity.ok(clientService.create(sdi));
    }
}