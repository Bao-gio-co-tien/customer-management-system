package soa.cms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(
	excludeFilters = @ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = soa.cms.corba.Config.class
	)
)
class CustomerManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
