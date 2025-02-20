package soa.group.project.cms.corba;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ORB org() throws Exception {
        String[] args = new String[]{};
        return ORB.init(args, null);
    }

    @Bean
    public POA rootPOA(ORB orb) throws Exception {
        return POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
    }

}
