package soa.cms.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CorbaService {

    private ORB orb;
    public CorbaService(@Qualifier("orb") ORB orb) {
        this.orb = orb;
    }
    private POA rootPOA;
    private NamingContextExt namingContext;

    @Autowired
    public CorbaService(ORB orb, @Qualifier("rootPOA") POA rootPOA, @Qualifier("namingContextExt") NamingContextExt namingContext) {
        this.orb = orb;
        this.rootPOA = rootPOA;
        this.namingContext = namingContext;
    }

    @PostConstruct
    public void initialize() {
        try {
            System.out.println("CORBA Service OK rồi");
        } catch (Exception e) {
            System.err.println("CORBA Service failed to initialize: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
