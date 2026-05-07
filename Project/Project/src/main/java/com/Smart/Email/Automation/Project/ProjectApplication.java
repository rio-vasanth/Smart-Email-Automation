package com.Smart.Email.Automation.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		System.setProperty("java.awt.headless", "false");
	}
	@Scheduled(fixedRate = 10000)
	public void testScheduler() {
		System.out.println("Scheduler is running...");
	}


}
