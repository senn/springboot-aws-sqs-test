package com.axxes.test.aws.sqs.test;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAwsSqsTestApplication {

	private static ThreadLocal<Integer> idCounter = ThreadLocal.withInitial(() -> 0);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsTestApplication.class, args);
		idCounter.remove();
	}

	@Bean
	public ApplicationRunner runner(Producer producer) {
		return args -> {
			while (true) {
				Thread.sleep(3000);
				int tempCount = idCounter.get();
				for (int i = tempCount; i < (tempCount + 10); i++) {
					producer.publishMessage(String.valueOf(i));
					idCounter.set(i);
				}
			}
		};
	}

}
