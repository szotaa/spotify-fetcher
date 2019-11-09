package pl.szotaa.spotifyfetcher;

import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;

public interface SpotifyPlayHistoryService {
    SpotifyRecentlyPlayedResponse getRecentlyPlayedTracks(String accessToken);
}
