package personal.Tu;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootUploadImagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadImagesApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary(){
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dzjoxccho",
				"api_key", "175631491224487",
				"api_secret", "TmeGg3OGrfTjPGmY4Wj9cBnNYy8",
				"secure", true
		));

		return cloudinary;

	}
}
