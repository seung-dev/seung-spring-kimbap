package seung.spring.kimbap.test.service;

import seung.java.kimchi.exception.SCastException;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

public interface STestS {

	public SResponse test0000(SRequest sRequest);
	
	public SResponse test0010(SRequest sRequest);
	
	public SResponse test0020(SRequest sRequest) throws SCastException;
	
	public SResponse test0030(SRequest sRequest) throws SCastException;
	
}
