package com.axxes.test.aws.sqs.test;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAwsSqsTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsTestApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(Producer producer) {
		return args -> {
			Thread.sleep(3000);
			for (int i = 0; i < 10; i++) {
				producer.publishMessage(String.valueOf(i));
			}
		};
	}

}
