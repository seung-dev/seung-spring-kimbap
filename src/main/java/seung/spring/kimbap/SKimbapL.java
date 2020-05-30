package seung.spring.kimbap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SStringU;
import seung.spring.boot.conf.SProperties;

@Slf4j
@Component
public class SKimbapL {

	@Resource(name="sProperties")
	private SProperties sProperties;
	
	@PostConstruct
	public void postRun() {
		
		log.debug("run");
		
		try {
			log.info(SStringU.toJson(sProperties, true));
		} catch (JsonProcessingException e) {
			log.error("Failed to check configProperties.", true);
		}
		
	}
	
}
