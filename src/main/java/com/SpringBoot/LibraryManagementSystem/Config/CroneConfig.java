package com.SpringBoot.LibraryManagementSystem.Config;


import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



@Configuration
@EnableScheduling
public class CroneConfig {
	
	private static final Logger log = LoggerFactory.getLogger(CroneConfig.class);
	
	@Scheduled(cron = "0 0 */1 * * *")
	public void sendMessage() {
	log.info("Current time is :: " + Calendar.getInstance().getTime());
	}

}
