package seung.spring.boot.conf;

import java.util.List;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SProperties {

	private Properties environment;
	
	private Properties swagger;
	
	private List<Properties> datasource;
	
	private Properties jpaVendorProperties;
	
	private Properties jpaProperties;
	
	@Builder
	public SProperties(
			Properties environment
			, Properties swagger
			, List<Properties> datasource
			, Properties jpaVendorProperties
			, Properties jpaProperties
			) {
		this.environment = environment;
		this.swagger = swagger;
		this.datasource = datasource;
		this.jpaVendorProperties = jpaVendorProperties;
		this.jpaProperties = jpaProperties;
	}
}
