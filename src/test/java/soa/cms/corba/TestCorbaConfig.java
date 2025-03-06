package soa.cms.corba;

import org.mockito.Mockito;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestCorbaConfig {

    @Bean
    @Primary
    public ORB orb() {
        return Mockito.mock(ORB.class);
    }

    @Bean
    @Primary
    public POA rootPOA() {
        return Mockito.mock(POA.class);
    }

    @Bean
    @Primary
    public NamingContextExt namingContextExt() {
        return Mockito.mock(NamingContextExt.class);
    }
}
