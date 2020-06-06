package seung.spring.kimbap.rest.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.exception.SKimchiException;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.exception.SKimbapError;
import seung.spring.kimbap.rest.SRestE;

@Slf4j
@Service(value = "sRestS")
public class SRestSI implements SRestS {

    @Resource(name = "sRestR")
    private SRestR sRestR;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Transactional
    @Override
    public SResponse rest0010(SRequest sRequest) {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder(sRequest.getData()).build();
        
        SRestE saveAndFlush = sRestR.saveAndFlush(
                SRestE
                    .builder()
                    .col01(sRequest.getData().getString("col01", ""))
                    .col02(sRequest.getData().getString("col02", ""))
                    .dateC(new Date())
                    .dateU(new Date())
                    .build()
                )
                ;
        sResponse.putData("rest0010", saveAndFlush);
        sResponse.setError_code(SKimbapError.Success.errorCode());
        
        return sResponse;
    }
    
    @Override
    public SResponse rest0020(SRequest sRequest) {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder(sRequest.getData()).build();
        
        sResponse.putData("rest0020", sRestR.findAll());
        sResponse.setError_code(SKimbapError.Success.errorCode());
        
        return sResponse;
    }
    
    @Override
    public SResponse rest0030(SRequest sRequest) throws SKimchiException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder(sRequest.getData()).build();
        
        SRestE sRest = sRestR.getOne(sRequest.getData().getLong("id"));
        sRest.setCol01(sRequest.getData().getString("col01", ""));
        sRest.setCol02(sRequest.getData().getString("col02", ""));
        sRest.setDateU(new Date());
        sResponse.putData("rest0030", sRestR.saveAndFlush(sRest));
        sResponse.setError_code(SKimbapError.Success.errorCode());
        
        return sResponse;
    }
    
    @Override
    public SResponse rest0040(SRequest sRequest) throws SKimchiException {
        
        log.debug("run");
        
        SResponse sResponse = SResponse.builder(sRequest.getData()).build();
        
        sRestR.deleteById(sRequest.getData().getLong("id"));
        sResponse.putData("rest0040", sRestR.existsById(sRequest.getData().getLong("id")) ? 0 : 1);
        sResponse.setError_code(SKimbapError.Success.errorCode());
        
        return sResponse;
    }
    
}
