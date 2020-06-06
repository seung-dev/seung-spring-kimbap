package seung.spring.kimbap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.exception.SKimchiException;
import seung.spring.boot.conf.SProperties;

@Slf4j
@Component
public class SKimbapL {

    @Resource(name="sProperties")
    private SProperties sProperties;
    
    @PostConstruct
    public void postRun() throws SKimchiException {
        
        log.debug("run");
        
        log.info(SString.toJson(sProperties, true));
        
    }
    
    @PreDestroy
    public void preShutdown() {
        
        log.debug("run");
        
        try {
            log.info(SString.toJson(sProperties, true));
        } catch (SKimchiException e) {
            log.error("Failed to print sProperties.", e);
        }
        
    }
    
}
