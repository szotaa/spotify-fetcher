package pl.szotaa.spotifyfetcher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;
import pl.szotaa.spotifyfetcher.util.AuthorizedRequestFactory;

@Service
public class SpotifyPlayHistoryServiceImpl implements SpotifyPlayHistoryService {

    private static final String SPOTIFY_RECENTLY_PLAYED_API_URL = "https://api.spotify.com/v1/me/player/recently-played?limit=50";

    private final RestTemplate restTemplate;

    public SpotifyPlayHistoryServiceImpl(@Qualifier("playHistoryService") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SpotifyRecentlyPlayedResponse getRecentlyPlayedTracks(String accessToken) {
        return restTemplate.exchange(
                SPOTIFY_RECENTLY_PLAYED_API_URL,
                HttpMethod.GET,
                AuthorizedRequestFactory.build(accessToken),
                SpotifyRecentlyPlayedResponse.class
        ).getBody();
    }
}
