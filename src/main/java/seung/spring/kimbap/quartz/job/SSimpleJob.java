package seung.spring.kimbap.quartz.job;

import javax.annotation.Resource;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.exception.SKimchiException;
import seung.spring.boot.conf.datasource.SMapperI;

@Slf4j
@Component
public class SSimpleJob extends QuartzJobBean implements InterruptableJob {

    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.info("interupted");
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        try {
            
            log.info("simpleJob.getJobDataMap={}", SString.toJson(context.getJobDetail().getJobDataMap(), true));
            
            log.info("test={}", SString.toJson(sMapperI.selectList("quartz0020"), true));
            
            Thread.sleep(1000 * 3);
            
        } catch (SKimchiException e) {
            throw new JobExecutionException(e);
        } catch (InterruptedException e) {
            throw new JobExecutionException(e);
        }
        
    }
    
}
