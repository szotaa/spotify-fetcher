package pl.szotaa.spotifyfetcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;

import java.util.Collections;
import java.util.Map;

@Service
public class SpotifyPlayHistoryServiceImpl implements SpotifyPlayHistoryService {

    private static final String SPOTIFY_RECENTLY_PLAYED_API_URL = "https://api.spotify.com/v1/me/player/recently-played";

    private final RestTemplate restTemplate;

    public SpotifyPlayHistoryServiceImpl(@Qualifier("playHistoryService") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SpotifyRecentlyPlayedResponse getRecentlyPlayedTracks(String accessToken) {
        return restTemplate.exchange(
                SPOTIFY_RECENTLY_PLAYED_API_URL,
                HttpMethod.GET,
                buildRequest(accessToken),
                SpotifyRecentlyPlayedResponse.class,
                getUriVariables()
        ).getBody();
    }

    private HttpEntity buildRequest(String accessToken) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        return new HttpEntity<>(headers);
    }

    private Map<String, Object> getUriVariables() {
        return Collections.singletonMap("limit", 50);
    }
}
