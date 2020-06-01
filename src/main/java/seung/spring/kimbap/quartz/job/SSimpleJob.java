package seung.spring.kimbap.quartz.job;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SStringU;

@Slf4j
public class SSimpleJob extends QuartzJobBean implements InterruptableJob {

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.info("interupted");
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("simpleJob.getJobDataMap={}", SStringU.toJson(context.getJobDetail().getJobDataMap(), true));
		} catch (JsonProcessingException e) {
			log.error("Failed to execute job.", e);
		}
	}
	
}
