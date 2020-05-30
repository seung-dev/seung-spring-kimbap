package seung.spring.kimbap.rest.service;

import seung.java.kimchi.exception.SCastException;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

public interface SRestS {

	public SResponse rest0000(SRequest sRequest);
	
	public SResponse rest0010(SRequest sRequest);
	
	public SResponse rest0020(SRequest sRequest) throws SCastException;
	
	public SResponse rest0030(SRequest sRequest) throws SCastException;
	
}
