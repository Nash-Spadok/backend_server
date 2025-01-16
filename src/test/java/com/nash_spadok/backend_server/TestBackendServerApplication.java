package com.nash_spadok.backend_server;

import org.springframework.boot.SpringApplication;

public class TestBackendServerApplication {

	public static void main(String[] args) {
		SpringApplication.from(BackendServerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
