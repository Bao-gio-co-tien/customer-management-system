package soa.cms.corba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import soa.cms.service.CustomerCareServiceImpl;
import soa.cms.service.CustomerInfoServiceImpl;
import soa.cms.service.CustomerUpdateServiceImpl;
import soa.cms.service.EmailMarketingServiceImpl;

import javax.annotation.PostConstruct;

@Component
@Profile("!test")
public class Server {
    @Autowired
    private CorbaService corbaService;

    @Autowired
    private CustomerInfoServiceImpl customerInfoService;

    @Autowired
    private CustomerUpdateServiceImpl customerUpdateService;

    @Autowired
    private EmailMarketingServiceImpl emailMarketingService;

    @Autowired
    private CustomerCareServiceImpl customerCareService;

    @PostConstruct
    public void initialize() {
        try {
            org.omg.CORBA.Object customerRef = corbaService.servantToReference(customerInfoService);
            corbaService.bindService(customerRef, "CustomerInfoService");

            org.omg.CORBA.Object emailRef = corbaService.servantToReference(emailMarketingService);
            corbaService.bindService(emailRef, "EmailMarketingService");

            org.omg.CORBA.Object customerUpdateRef = corbaService.servantToReference(customerUpdateService);
            corbaService.bindService(customerUpdateRef, "CustomerUpdateService");

            org.omg.CORBA.Object customerCareRef = corbaService.servantToReference(customerCareService);
            corbaService.bindService(customerCareRef, "CustomerCareService");

            System.out.println("All CORBA services registered successfully");
        } catch (Exception e) {
            System.err.println("ERROR initializing CORBA server: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize CORBA server", e);
        }
    }
}