package soa.cms.corba;

import CMS.CustomerCareServicePOA;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soa.cms.service.CustomerCareServiceImpl;
import soa.cms.service.CustomerInfoServiceImpl;
import soa.cms.service.CustomerUpdateServiceImpl;
import soa.cms.service.EmailMarketingServiceImpl;

@Component
public class Server implements CommandLineRunner {
    @Autowired
    private ORB orb;

    @Autowired
    private POA rootPOA;

    @Autowired
    private CustomerInfoServiceImpl customerInfoService;

    @Autowired
    private CustomerUpdateServiceImpl customerUpdateService;

    @Autowired
    private EmailMarketingServiceImpl emailMarketingService;

    @Autowired
    private CustomerCareServiceImpl customerCareService;


    @Override
    public void run(String... args) throws Exception {
        rootPOA.the_POAManager().activate();

        org.omg.CORBA.Object customerRef = rootPOA.servant_to_reference(customerInfoService);
        bindService(customerRef, "CustomerInfoService");

        org.omg.CORBA.Object emailRef = rootPOA.servant_to_reference(emailMarketingService);
        bindService(emailRef, "EmailMarketingService");

        System.out.println("CORBA Service are ready");
        orb.run();
    }

    private void bindService(org.omg.CORBA.Object ref, String name) throws Exception {
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        NameComponent path[] =ncRef.to_name(name);
        ncRef.rebind(path, ref);
    }
}

