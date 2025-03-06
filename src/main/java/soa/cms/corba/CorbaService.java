package soa.cms.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingIteratorHolder;

@Service
public class CorbaService {

    private final ORB orb;
    private final POA rootPOA;
    private final NamingContextExt namingContext;

    @Autowired
    public CorbaService(ORB orb, POA rootPOA, NamingContextExt namingContext) {
        this.orb = orb;
        this.rootPOA = rootPOA;
        this.namingContext = namingContext;
    }

    @PostConstruct
    public void initialize() {
        try {
            boolean namingServiceAvailable = isNamingServiceAvailable();

            if (namingServiceAvailable) {
                System.out.println("CORBA Service initialized successfully. NameService is reachable.");
            } else {
                System.err.println("WARNING: CORBA Service initialized, but NameService might not be reachable.");
            }

            Thread orbThread = new Thread(() -> {
                orb.run();
            });
            orbThread.setDaemon(true);
            orbThread.start();

        } catch (Exception e) {
            System.err.println("CORBA Service failed to initialize: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize CORBA Service", e);
        }
    }

    private boolean isNamingServiceAvailable() {
        try {
            BindingListHolder blh = new BindingListHolder();
            BindingIteratorHolder bih = new BindingIteratorHolder();
            namingContext.list(1, blh, bih);
            return true;
        } catch (Exception e) {
            System.err.println("NameService check failed: " + e.getMessage());
            return false;
        }
    }

    public void bindService(org.omg.CORBA.Object ref, String name) throws Exception {
        try {
            org.omg.CosNaming.NameComponent path[] = namingContext.to_name(name);
            namingContext.rebind(path, ref);
            System.out.println("Successfully bound service: " + name);
        } catch (Exception e) {
            System.err.println("ERROR binding " + name + ": " + e.getMessage());
            throw e;
        }
    }

    public org.omg.CORBA.Object servantToReference(org.omg.PortableServer.Servant servant) throws Exception {
        try {
            return rootPOA.servant_to_reference(servant);
        } catch (Exception e) {
            System.err.println("ERROR converting servant to reference: " + e.getMessage());
            throw e;
        }
    }
}