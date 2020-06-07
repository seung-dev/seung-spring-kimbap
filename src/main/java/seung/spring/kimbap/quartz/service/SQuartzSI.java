package seung.spring.kimbap.quartz.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.exception.SIllegalArgumentException;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.quartz.SQuartzFactory;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.util.SKimbapError;
import seung.spring.kimbap.util.SKimbapException;

@Slf4j
@Service(value = "sQuartzS")
public class SQuartzSI implements SQuartzS {

    @Autowired
    private SQuartzFactory sQuartzFactory;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Lazy
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    
    @SuppressWarnings("unchecked")
    @Override
    public SResponse quartz0010(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            if(scheduler.checkExists(jobKey)) {
                
                throw new SIllegalArgumentException("Requested job alreay exists.");
                
            } else {
                
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("qwer", "6789");
                JobDetail jobDetail = JobBuilder.newJob()
                        .withIdentity(name, group)
                        .withDescription(sRequest.getData().getString("job_description", ""))
                        .storeDurably(true)
                        .ofType((Class<? extends QuartzJobBean>) Class.forName(sRequest.getData().getString("job_class", "")))
                        .setJobData(jobDataMap)
                        .build()
                        ;
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(name, group)
                        .startAt(SDate.toDate(sRequest.getData().getString("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss"))
                        .withDescription(sRequest.getData().getString("job_description", ""))
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000 * 3).repeatForever())
                        .build()
                        ;
                
                Date quartz0010 = scheduler.scheduleJob(jobDetail, trigger);
                sResponse.putResult("quartz0010", SDate.getDateString("yyyy-MM-dd HH:mm:ss", quartz0010));
                
                sResponse.setError_code(SKimbapError.Success.errorCode());
                
            }// end of check exists
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        } catch (SIllegalArgumentException e) {
            throw new SKimbapException(e);
        } catch (ClassNotFoundException e) {
            throw new SKimbapException(e);
        } catch (SKimchiException e) {
            throw new SKimbapException(e);
        }
        
        return sResponse;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public SResponse quartz0011(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            if(scheduler.checkExists(jobKey)) {
                
                throw new SIllegalArgumentException("Requested job alreay exists.");
                
            } else {
                
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("abcd", "1234");
                JobDetail jobDetail = JobBuilder.newJob()
                        .withIdentity(name, group)
                        .withDescription(sRequest.getData().getString("job_description", ""))
                        .storeDurably(true)
                        .ofType((Class<? extends QuartzJobBean>) Class.forName(sRequest.getData().getString("job_class", "")))
                        .setJobData(jobDataMap)
                        .build()
                        ;
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(name, group)
                        .startAt(SDate.toDate(sRequest.getData().getString("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss"))
                        .withDescription(sRequest.getData().getString("job_description", ""))
                        .withSchedule(CronScheduleBuilder.cronSchedule(sRequest.getData().getString("cron_expr", "")).inTimeZone(TimeZone.getDefault()))
                        .build()
                        ;
                
                Date quartz0011 = scheduler.scheduleJob(jobDetail, trigger);
                sResponse.putResult("quartz0011", SDate.getDateString("yyyy-MM-dd HH:mm:ss", quartz0011));
                
            }// end of check exists
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        } catch (SIllegalArgumentException e) {
            throw new SKimbapException(e);
        } catch (ClassNotFoundException e) {
            throw new SKimbapException(e);
        } catch (SKimchiException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0020(SRequest sRequest) {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        sResponse.putResult("quartz0020", sMapperI.selectList("quartz0020"));
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public SResponse quartz0021(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            List<SLinkedHashMap> quartz0021 = new ArrayList<>();
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            sResponse.putResult("instance_id", scheduler.getSchedulerInstanceId());
            sResponse.putResult("name", scheduler.getSchedulerName());
            
            SLinkedHashMap       job           = null;
            List<Trigger>        triggersOfJob = null;
            List<SLinkedHashMap> triggers      = null;
            SLinkedHashMap       trigger       = null;
            for(String jobGroupName : scheduler.getJobGroupNames()) {
                log.info(jobGroupName);
                for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName))) {
                    job = new SLinkedHashMap();
                    job.put("job_group", jobKey.getGroup());
                    job.put("job_name", jobKey.getName());
                    job.put("job_class", scheduler.getJobDetail(jobKey).getJobClass().getName());
                    triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    if(triggersOfJob.isEmpty()) {
                        quartz0021.add(job);
                    } else {
                        triggers = new ArrayList<>();
                        for(Trigger t : triggersOfJob) {
                            trigger = new SLinkedHashMap();
                            trigger.put("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss", t.getStartTime()));
                            trigger.put("next_fire_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss", t.getNextFireTime()));
                            trigger.put("previous_fire_time", t.getPreviousFireTime() == null ? "" : SDate.getDateString("yyyy-MM-dd HH:mm:ss", t.getPreviousFireTime()));
                            trigger.put("state", scheduler.getTriggerState(t.getKey()).name());
                            triggers.add(trigger);
                        }// end of triggers
                        job.put("trigger", triggers);
                    }// end of check empty
                    quartz0021.add(job);
                }// end of job keys
            }// end of job group names
            
            sResponse.putResult("quartz0021", quartz0021);
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0030(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            sResponse.putResult("quartz0030", scheduler.unscheduleJob(TriggerKey.triggerKey(name, group)) ? "1" : "0");
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0031(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            if(!scheduler.checkExists(jobKey)) {
                
                throw new SIllegalArgumentException("Requested job does not exists.");
                
            } else {
                
                Trigger trigger = sQuartzFactory.simpleTrigger(
                        sRequest.getData().getString("job_group", "")
                        , sRequest.getData().getString("job_name", "")
                        , sRequest.getData().isEmpty("start_time") ? new Date() : SDate.toDate(sRequest.getData().getString("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss")
                        , sRequest.getData().getLong("start_delay", 5)
                        , sRequest.getData().getInt("repeat_count", 0)
                        , sRequest.getData().getLong("repeat_interval", 0)
                        , sRequest.getData().getInt("misfire_instruction", 1)
                        );
                
                Date quartz0031 = scheduler.rescheduleJob(TriggerKey.triggerKey(name, group), trigger);
                sResponse.putResult("quartz0031", SDate.getDateString("yyyy-MM-dd HH:mm:ss", quartz0031));
                
            }
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        } catch (SIllegalArgumentException e) {
            throw new SKimbapException(e);
        } catch (SKimchiException e) {
            throw new SKimbapException(e);
        }// end of check exists
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0032(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            if(!scheduler.checkExists(jobKey)) {
                
                throw new SIllegalArgumentException("Requested job does not exists.");
                
            } else {
                
                Trigger trigger = sQuartzFactory.cronTrigger(
                        sRequest.getData().getString("job_group", "")
                        , sRequest.getData().getString("job_name", "")
                        , sRequest.getData().isEmpty("start_time") ? new Date() : SDate.toDate(sRequest.getData().getString("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss")
                        , sRequest.getData().getLong("start_delay", 5)
                        , sRequest.getData().getString("cron_expr", "")
                        , sRequest.getData().getInt("misfire_instruction", 0)
                        );
                
                Date quartz0032 = scheduler.rescheduleJob(TriggerKey.triggerKey(name, group), trigger);
                sResponse.putResult("quartz0032", SDate.getDateString("yyyy-MM-dd HH:mm:ss", quartz0032));
                
            }
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        } catch (SIllegalArgumentException e) {
            throw new SKimbapException(e);
        } catch (ParseException e) {
            throw new SKimbapException(e);
        } catch (SKimchiException e) {
            throw new SKimbapException(e);
        }// end of check exists
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0040(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            sResponse.putResult("quartz0040", scheduler.deleteJob(jobKey) ? "1" : "0");
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0050(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            scheduler.triggerJob(jobKey);
            
            sResponse.putResult("quartz0050", "1");
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0051(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            scheduler.pauseJob(jobKey);
            
            sResponse.putResult("quartz0051", "1");
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
    @Override
    public SResponse quartz0052(SRequest sRequest) throws SKimbapException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder()
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            
            String group = sRequest.getData().getString("job_group", "");
            String name = sRequest.getData().getString("job_name", "");
            
            JobKey jobKey = JobKey.jobKey(name, group);
            
            scheduler.resumeJob(jobKey);
            
            sResponse.putResult("quartz0052", "1");
            
        } catch (SchedulerException e) {
            throw new SKimbapException(e);
        }
        
        sResponse.setError_code(SKimbapError.Success.errorCode());
        return sResponse;
    }
    
}
