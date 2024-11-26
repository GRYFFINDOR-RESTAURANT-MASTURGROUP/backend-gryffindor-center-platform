package pe.edu.uni.restaurant.gryffindor_center_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GryffindorCenterPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(GryffindorCenterPlatformApplication.class, args);
	}

}
