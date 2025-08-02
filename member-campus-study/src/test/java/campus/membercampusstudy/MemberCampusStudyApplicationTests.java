package campus.membercampusstudy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MemberCampusStudyApplicationTests {

	@Test
	void contextLoads() {
	}

}
