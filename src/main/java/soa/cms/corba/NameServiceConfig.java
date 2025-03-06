package soa.cms.corba;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("nameserver")
public class NameServiceConfig {

    @PostConstruct
    public void startNameService() {
        try {
            System.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
            System.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
            System.setProperty("OAPort", "2809");

            String[] nameServerArgs = {"-DOAPort=2809"};

            Thread nameServerThread = new Thread(() -> {
                try {
                    org.jacorb.naming.NameServer.main(nameServerArgs);
                } catch (Exception e) {
                    System.err.println("Error starting NameServer: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            nameServerThread.setDaemon(false);
            nameServerThread.start();

            System.out.println("JacORB Name Service started on port 2809");
        } catch (Exception e) {
            System.err.println("Failed to start NameServer: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to start NameServer", e);
        }
    }
}