package seung.spring.kimbap.test.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDateU;
import seung.java.kimchi.exception.SCastException;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.SKimbapError;
import seung.spring.kimbap.test.STest;

@Slf4j
@Service(value = "sTestS")
public class STestSI implements STestS {

	@Resource(name = "sTestR")
	private STestR sTestR;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@Override
	public SResponse test0000(SRequest sRequest) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		sResponse.putData("test0000", sTestR.findAll());
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Transactional
	@Override
	public SResponse test0010(SRequest sRequest) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		STest saveAndFlush = sTestR.saveAndFlush(
				STest
					.builder()
					.col01(sRequest.getData().getString("col01", ""))
					.col02(sRequest.getData().getString("col02", ""))
					.date_c(new Date())
					.date_u(new Date())
					.build()
				)
				;
		sResponse.putData("test0010", saveAndFlush);
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Override
	public SResponse test0020(SRequest sRequest) throws SCastException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		STest sTest = sTestR.getOne(sRequest.getData().getLong("id"));
		sTest.setCol01(sRequest.getData().getString("col01", ""));
		sTest.setCol02(sRequest.getData().getString("col02", ""));
		sTest.setDate_u(new Date());
		sResponse.putData("test0020", sTestR.saveAndFlush(sTest));
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Override
	public SResponse test0030(SRequest sRequest) throws SCastException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		sTestR.deleteById(sRequest.getData().getLong("id"));
		sResponse.putData("test0030", sTestR.existsById(sRequest.getData().getLong("id")) ? 0 : 1);
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
}
