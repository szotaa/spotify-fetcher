package pl.szotaa.spotifyfetcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.szotaa.spotifyfetcher.dto.RefreshTokenResponse;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class SpotifyAuthServiceImpl implements SpotifyAuthService {

    private static final String SPOTIFY_AUTH_URL = "https://accounts.spotify.com/api/token";

    private final RestTemplate restTemplate;
    private final String refreshToken;

    public SpotifyAuthServiceImpl(
            @Qualifier("authService") RestTemplate restTemplate,
            @Value("${spotify.refresh.token}") String refreshToken
    ) {
        this.restTemplate = restTemplate;
        this.refreshToken = refreshToken;
    }

    @Override
    public String getAccessToken() {
        var response = Optional.ofNullable(
                restTemplate.postForObject(SPOTIFY_AUTH_URL, buildRequest(), RefreshTokenResponse.class)
        ).orElseThrow(() -> new IllegalStateException("Auth service response cannot be null!"));
        return response.getAccessToken();
    }

    private HttpEntity buildRequest() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        var body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", URLEncoder.encode(refreshToken, Charset.defaultCharset()));
        return new HttpEntity<>(body, headers);
    }
}
