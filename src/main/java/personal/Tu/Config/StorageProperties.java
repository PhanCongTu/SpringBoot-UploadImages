package personal.Tu.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public StorageProperties(String location) {
		super();
		this.location = location;
	}

	public StorageProperties() {
		super();
	}
    
    
}
