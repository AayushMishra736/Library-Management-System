package com.SpringBoot.LibraryManagementSystem.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkUploadBookController {
	
	private static final Logger log = LoggerFactory.getLogger(BulkUploadBookController.class);
	
	@Autowired
	private JobLauncher joblauncher;
	
	@Autowired
	private Job job;
	
	@GetMapping("/BulkUpload")
	public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution= joblauncher.run(job, parameters);
		log.info("JobExecution :"+jobExecution.getStatus());
		log.info("Batch is Running.............");
		while(jobExecution.isRunning()) {
			log.info("...");
		}
		return jobExecution.getStatus();
	}
	
	

}
