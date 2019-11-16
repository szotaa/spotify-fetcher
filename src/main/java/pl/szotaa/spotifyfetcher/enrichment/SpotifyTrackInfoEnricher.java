package pl.szotaa.spotifyfetcher.enrichment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.szotaa.spotifyfetcher.SpotifyAuthService;
import pl.szotaa.spotifyfetcher.dto.SpotifyTrackInfoResponse;
import pl.szotaa.spotifyfetcher.mapping.SpotifyTrackInfoMapper;
import pl.szotaa.spotifyfetcher.persistence.Track;
import pl.szotaa.spotifyfetcher.util.AuthorizedRequestFactory;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyTrackInfoEnricher implements TrackInfoEnricher {

    private static final String ENRICHMENT_URL_TEMPLATE = "https://api.spotify.com/v1/audio-features/%s";
    private final SpotifyTrackInfoMapper mapper;
    private final RestTemplateBuilder restTemplateBuilder;
    private final SpotifyAuthService spotifyAuthService;

    @Override
    public Collection<Track> enrich(Collection<Track> tracks) {
        return tracks.stream()
                .map(this::enrich)
                .collect(Collectors.toList());
    }

    @Override
    public Track enrich(Track track) {
        var restTemplate = restTemplateBuilder.build();
        var accessToken = spotifyAuthService.getAccessToken();
        var response = restTemplate.exchange(
                String.format(ENRICHMENT_URL_TEMPLATE, track.getSpotifyId()),
                HttpMethod.GET,
                AuthorizedRequestFactory.build(accessToken),
                SpotifyTrackInfoResponse.class
        );

        var trackInfo = mapper.toEntity(response.getBody());
        track.setTrackInfo(trackInfo);
        return track;
    }
}
