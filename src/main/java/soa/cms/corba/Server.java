package soa.cms.corba;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import soa.cms.service.CustomerCareServiceImpl;
import soa.cms.service.CustomerInfoServiceImpl;
import soa.cms.service.CustomerUpdateServiceImpl;
import soa.cms.service.EmailMarketingServiceImpl;

@Component
@Profile("!test")
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
        try {
            rootPOA.the_POAManager().activate();

            org.omg.CORBA.Object customerRef = rootPOA.servant_to_reference(customerInfoService);
            bindService(customerRef, "CustomerInfoService");

            org.omg.CORBA.Object emailRef = rootPOA.servant_to_reference(emailMarketingService);
            bindService(emailRef, "EmailMarketingService");

            org.omg.CORBA.Object customerUpdateRef = rootPOA.servant_to_reference(customerUpdateService);
            bindService(customerUpdateRef, "CustomerUpdateService");

            org.omg.CORBA.Object customerCareRef = rootPOA.servant_to_reference(customerCareService);
            bindService(customerCareRef, "CustomerCareService");

            System.out.println("CORBA Service OK rồi");
            new Thread(() -> {
                orb.run();
            }).start();
        } catch (Exception e) {
            System.err.println("ERROR starting " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void bindService(org.omg.CORBA.Object ref, String name) throws Exception {
        try {
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, ref);
        } catch (Exception e) {
            System.err.println("ERROR binding" + name + e.getMessage());
            throw e;
        }
    }
}

