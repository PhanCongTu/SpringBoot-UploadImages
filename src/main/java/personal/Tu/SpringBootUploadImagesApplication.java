package personal.Tu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import personal.Tu.Config.StorageProperties;
import personal.Tu.Service.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootUploadImagesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootUploadImagesApplication.class, args);
	}

	// Thêm cấu hình storage
	@Bean
	CommandLineRunner init(IStorageService storageService){
		return (args -> {
			storageService.init();
		});
	}
//	@Bean
//	public CommonsMultipartResolver multipartResolver(){
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		return resolver;
//	}
}
