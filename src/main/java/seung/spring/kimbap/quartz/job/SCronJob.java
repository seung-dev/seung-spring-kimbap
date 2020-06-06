package seung.spring.kimbap.quartz.job;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.exception.SKimchiException;

@Slf4j
public class SCronJob extends QuartzJobBean implements InterruptableJob {

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("interupted");
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            log.info("cronJob.getJobDataMap={}", SString.toJson(context.getJobDetail().getJobDataMap(), true));
        } catch (SKimchiException e) {
            log.error("Failed to print jobExecutionContext.jobDetail.jobDataMap.", e);
        }
    }
    
}
