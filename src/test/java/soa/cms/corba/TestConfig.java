package soa.cms.corba;

import org.jacorb.orb.ORB;
import org.jacorb.poa.POA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class TestConfig {
    @Bean
    @Primary
    public ORB testOrb() {
        return mock(ORB.class);
    }

    @Bean
    @Primary
    public POA testRootPOA() {
        return mock(POA.class);
    }
}
