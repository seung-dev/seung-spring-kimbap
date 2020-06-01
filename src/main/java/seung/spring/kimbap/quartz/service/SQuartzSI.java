package seung.spring.kimbap.quartz.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDateU;
import seung.java.kimchi.exception.SCastException;
import seung.java.kimchi.exception.SIllegalArgumentException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.quartz.SQuartzFactory;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.SKimbapError;

@Slf4j
@Service(value = "sQuartzS")
public class SQuartzSI implements SQuartzS {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SQuartzFactory sQuartzFactory;
	
	@Lazy
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0010(SRequest sRequest) throws SCastException, ClassNotFoundException, ParseException, SchedulerException, SIllegalArgumentException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		if(scheduler.checkExists(jobKey)) {
			
			throw new SIllegalArgumentException("Requested job alreay exists.");
			
		} else {
			
			JobDetail jobDetail = sQuartzFactory.jobDetail(
					applicationContext
					, group
					, name
					, (Class<? extends QuartzJobBean>) Class.forName(sRequest.getData().getString("job_class", ""))
					, true
					, new JobDataMap()
					);
			Trigger trigger = sQuartzFactory.simpleTrigger(
					sRequest.getData().getString("job_group", "")
					, sRequest.getData().getString("job_name", "")
					, SDateU.toDate(sRequest.getData().getString("start_time", SDateU.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss")
					, sRequest.getData().getInt("repeat_count", 0)
					, sRequest.getData().getLong("repeat_interval", 0)
					, sRequest.getData().getInt("misfire_instruction", 1)
					);
			
			Date quartz0010 = scheduler.scheduleJob(jobDetail, trigger);
			sResponse.getData().put("quartz0010", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", quartz0010));
			
			sResponse.setError_code(SKimbapError.Success.errorCode());
			
		}// end of check exists
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0011(SRequest sRequest) throws SCastException, ClassNotFoundException, ParseException, SchedulerException, SIllegalArgumentException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		if(scheduler.checkExists(jobKey)) {
			
			throw new SIllegalArgumentException("Requested job alreay exists.");
			
		} else {
			
			JobDetail jobDetail = sQuartzFactory.jobDetail(
					applicationContext
					, group
					, name
					, (Class<? extends QuartzJobBean>) Class.forName(sRequest.getData().getString("job_class", ""))
					, true
					, new JobDataMap()
					);
			Trigger trigger = sQuartzFactory.cronTrigger(
					sRequest.getData().getString("job_group", "")
					, sRequest.getData().getString("job_name", "")
					, SDateU.toDate(sRequest.getData().getString("start_time", ""), "yyyy-MM-dd HH:mm:ss")
					, sRequest.getData().getString("cron_expr", "")
					, sRequest.getData().getInt("misfire_instruction", 0)
					);
			
			Date quartz0011 = scheduler.scheduleJob(jobDetail, trigger);
			sResponse.getData().put("quartz0011", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", quartz0011));
			
		}// end of check exists
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0020(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		List<SLinkedHashMap> quartz0020 = new ArrayList<>();
		
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		sResponse.putData("instance_id", scheduler.getSchedulerInstanceId());
		sResponse.putData("name", scheduler.getSchedulerName());
		
		SLinkedHashMap       job           = null;
		List<Trigger>        triggersOfJob = null;
		List<SLinkedHashMap> triggers      = null;
		SLinkedHashMap       trigger       = null;
		for(String jobGroupName : scheduler.getJobGroupNames()) {
			for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName))) {
				job = new SLinkedHashMap();
				job.put("job_group", jobKey.getGroup());
				job.put("job_name", jobKey.getName());
				job.put("job_class", scheduler.getJobDetail(jobKey).getJobClass().getName());
				triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				if(triggersOfJob.isEmpty()) {
					quartz0020.add(job);
				} else {
					triggers = new ArrayList<>();
					for(Trigger t : triggersOfJob) {
						trigger = new SLinkedHashMap();
						trigger.put("start_time", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", t.getStartTime()));
						trigger.put("next_fire_time", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", t.getNextFireTime()));
						trigger.put("previous_fire_time", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", t.getPreviousFireTime()));
						trigger.put("state", scheduler.getTriggerState(t.getKey()).name());
						triggers.add(trigger);
					}// end of triggers
					job.put("trigger", triggers);
				}// end of check empty
				quartz0020.add(job);
			}// end of job keys
		}// end of job group names
		
		sResponse.getData().put("quartz0020", quartz0020);
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0030(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		sResponse.getData().put("quartz0030", scheduler.unscheduleJob(TriggerKey.triggerKey(name, group)) ? "1" : "0");
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0031(SRequest sRequest) throws SIllegalArgumentException, ParseException, SCastException, SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
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
					, SDateU.toDate(sRequest.getData().getString("start_time", SDateU.getDateString("yyyy-MM-dd HH:mm:ss")), "yyyy-MM-dd HH:mm:ss")
					, sRequest.getData().getInt("repeat_count", 0)
					, sRequest.getData().getLong("repeat_interval", 0)
					, sRequest.getData().getInt("misfire_instruction", 1)
					);
			
			Date quartz0031 = scheduler.rescheduleJob(TriggerKey.triggerKey(name, group), trigger);
			sResponse.getData().put("quartz0031", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", quartz0031));
			
		}// end of check exists
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0032(SRequest sRequest) throws SchedulerException, SIllegalArgumentException, ParseException, SCastException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
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
					, SDateU.toDate(sRequest.getData().getString("start_time", ""), "yyyy-MM-dd HH:mm:ss")
					, sRequest.getData().getString("cron_expr", "")
					, sRequest.getData().getInt("misfire_instruction", 0)
					);
			
			Date quartz0032 = scheduler.rescheduleJob(TriggerKey.triggerKey(name, group), trigger);
			sResponse.getData().put("quartz0032", SDateU.getDateInteger("yyyy-MM-dd HH:mm:ss", quartz0032));
			
		}// end of check exists
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0040(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		sResponse.getData().put("quartz0040", scheduler.deleteJob(jobKey) ? "1" : "0");
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0050(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		scheduler.triggerJob(jobKey);
		
		sResponse.getData().put("quartz0050", "1");
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0051(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		scheduler.pauseJob(jobKey);
		
		sResponse.getData().put("quartz0051", "1");
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0052(SRequest sRequest) throws SchedulerException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		String group = sRequest.getData().getString("job_group", "");
		String name = sRequest.getData().getString("job_name", "");
		
		JobKey jobKey = JobKey.jobKey(name, group);
		
		scheduler.resumeJob(jobKey);
		
		sResponse.getData().put("quartz0052", "1");
		
		sResponse.setError_code(SKimbapError.Success.errorCode());
		return sResponse;
	}
	
}
