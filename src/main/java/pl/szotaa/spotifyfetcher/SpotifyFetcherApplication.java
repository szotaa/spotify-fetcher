package pl.szotaa.spotifyfetcher;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class SpotifyFetcherApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpotifyFetcherApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

}
