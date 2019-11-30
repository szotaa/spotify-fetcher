package pl.szotaa.spotifyfetcher.enrichment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import pl.szotaa.spotifyfetcher.SpotifyAuthService;
import pl.szotaa.spotifyfetcher.dto.SpotifyAlbumInfoResponse;
import pl.szotaa.spotifyfetcher.mapping.AlbumGenreMapper;
import pl.szotaa.spotifyfetcher.persistence.Album;
import pl.szotaa.spotifyfetcher.util.AuthorizedRequestFactory;

@Component
@RequiredArgsConstructor
public class AlbumGenreEnricher implements AlbumInfoEnricher {

    private static final String ENRICHMENT_URL_TEMPLATE = "https://api.spotify.com/v1/albums/%s";
    private final RestTemplateBuilder restTemplateBuilder;
    private final SpotifyAuthService spotifyAuthService;
    private final AlbumGenreMapper mapper;

    @Override
    public Album enrich(Album album) {
        var restTemplate = restTemplateBuilder.build();
        var accessToken = spotifyAuthService.getAccessToken();
        var response = restTemplate.exchange(
                String.format(ENRICHMENT_URL_TEMPLATE, album.getSpotifyId()),
                HttpMethod.GET,
                AuthorizedRequestFactory.build(accessToken),
                SpotifyAlbumInfoResponse.class
        );
        album.setGenres(mapper.toEntities(response.getBody()));
        return album;
    }
}
