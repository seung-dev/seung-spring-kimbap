package seung.spring.kimbap.quartz.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SStringU;

@Slf4j
public class SCronJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("cronJob.getJobDataMap={}", SStringU.toJson(context.getJobDetail().getJobDataMap(), true));
		} catch (JsonProcessingException e) {
			log.error("Failed to execute job.", e);
		}
	}
	
}
