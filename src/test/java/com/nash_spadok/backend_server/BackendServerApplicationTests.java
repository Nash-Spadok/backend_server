package com.nash_spadok.backend_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BackendServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
