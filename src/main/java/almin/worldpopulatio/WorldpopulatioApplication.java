package almin.worldpopulatio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorldpopulatioApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldpopulatioApplication.class, args);
	}

}
