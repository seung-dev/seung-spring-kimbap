package seung.spring.kimbap.quartz.service;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.util.SKimbapException;

public interface SQuartzS {

    public SResponse quartz0010(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0011(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0020(SRequest sRequest);
    
    public SResponse quartz0021(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0030(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0031(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0032(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0040(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0050(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0051(SRequest sRequest) throws SKimbapException;
    
    public SResponse quartz0052(SRequest sRequest) throws SKimbapException;
    
}
