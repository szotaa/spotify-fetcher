package pl.szotaa.spotifyfetcher.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("authService")
    public RestTemplate authServiceRestTemplate(
            RestTemplateBuilder builder,
            @Value("${spotify.client.id}") String clientId,
            @Value("${spotify.client.secret}") String clientSecret
    ) {
        return builder.basicAuthentication(clientId, clientSecret).build();
    }

    @Bean
    @Qualifier("playHistoryService")
    public RestTemplate playHistoryServiceRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
