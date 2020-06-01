package seung.spring.kimbap.quartz.service;

import java.text.ParseException;

import org.quartz.SchedulerException;

import seung.java.kimchi.exception.SCastException;
import seung.java.kimchi.exception.SIllegalArgumentException;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

public interface SQuartzS {

	public SResponse quartz0010(SRequest sRequest) throws SCastException, ClassNotFoundException, ParseException, SchedulerException, SIllegalArgumentException;
	
	public SResponse quartz0011(SRequest sRequest) throws SCastException, ClassNotFoundException, ParseException, SchedulerException, SIllegalArgumentException;
	
	public SResponse quartz0020(SRequest sRequest) throws SchedulerException;
	
	public SResponse quartz0030(SRequest sRequest) throws SchedulerException;
	
	public SResponse quartz0031(SRequest sRequest) throws SIllegalArgumentException, ParseException, SCastException, SchedulerException;
	
	public SResponse quartz0032(SRequest sRequest) throws SIllegalArgumentException, ParseException, SCastException, SchedulerException;
	
	public SResponse quartz0040(SRequest sRequest) throws SchedulerException;
	
	public SResponse quartz0050(SRequest sRequest) throws SchedulerException;
	
	public SResponse quartz0051(SRequest sRequest) throws SchedulerException;
	
	public SResponse quartz0052(SRequest sRequest) throws SchedulerException;
	
}
