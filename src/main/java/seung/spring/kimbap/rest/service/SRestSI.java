package seung.spring.kimbap.rest.service;

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
import seung.spring.kimbap.rest.SRest;

@Slf4j
@Service(value = "sRestS")
public class SRestSI implements SRestS {

	@Resource(name = "sRestR")
	private SRestR sRestR;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@Override
	public SResponse rest0000(SRequest sRequest) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		sResponse.putData("rest0000", sRestR.findAll());
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Transactional
	@Override
	public SResponse rest0010(SRequest sRequest) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		SRest saveAndFlush = sRestR.saveAndFlush(
				SRest
					.builder()
					.col01(sRequest.getData().getString("col01", ""))
					.col02(sRequest.getData().getString("col02", ""))
					.date_c(new Date())
					.date_u(new Date())
					.build()
				)
				;
		sResponse.putData("rest0010", saveAndFlush);
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Override
	public SResponse rest0020(SRequest sRequest) throws SCastException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		SRest sRest = sRestR.getOne(sRequest.getData().getLong("id"));
		sRest.setCol01(sRequest.getData().getString("col01", ""));
		sRest.setCol02(sRequest.getData().getString("col02", ""));
		sRest.setDate_u(new Date());
		sResponse.putData("rest0020", sRestR.saveAndFlush(sRest));
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
	@Override
	public SResponse rest0030(SRequest sRequest) throws SCastException {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder(sRequest.getData()).build();
		
		sRestR.deleteById(sRequest.getData().getLong("id"));
		sResponse.putData("rest0030", sRestR.existsById(sRequest.getData().getLong("id")) ? 0 : 1);
		sResponse.setError_code(SKimbapError.Success.errorCode());
		
		return sResponse;
	}
	
}
