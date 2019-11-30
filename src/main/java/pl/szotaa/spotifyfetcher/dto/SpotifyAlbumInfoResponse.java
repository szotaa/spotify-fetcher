package pl.szotaa.spotifyfetcher.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpotifyAlbumInfoResponse {
    private String[] genres;
}
