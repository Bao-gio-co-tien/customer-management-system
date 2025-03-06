package soa.cms.corba;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class NameServiceConfig {

    @Bean
    @Profile("nameserver")
    public CommandLineRunner nameServiceRunner() {
        return (args) -> {
            System.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
            System.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
            System.setProperty("OAPort", "2809");

            String[] nameServerArgs = {"-DOAPort=2809"};

            org.jacorb.naming.NameServer.main(nameServerArgs);
        };
    }

}
