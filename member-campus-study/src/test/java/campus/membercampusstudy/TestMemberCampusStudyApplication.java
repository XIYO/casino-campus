package campus.membercampusstudy;

import org.springframework.boot.SpringApplication;

public class TestMemberCampusStudyApplication {

	public static void main(String[] args) {
		SpringApplication.from(MemberCampusStudyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
