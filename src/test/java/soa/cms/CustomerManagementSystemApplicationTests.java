package soa.cms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import soa.cms.corba.TestConfig;
import soa.cms.corba.TestCorbaConfig;

@SpringBootTest
@ActiveProfiles("test")
@Import({TestConfig.class, TestCorbaConfig.class})
class CustomerManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
