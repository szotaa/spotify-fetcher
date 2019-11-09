package pl.szotaa.spotifyfetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyRecentlyPlayedResponse {

    private Item[] items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {

        private Track track;

        @JsonProperty("played_at")
        private LocalDateTime playedAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Track {
        @JsonProperty("id")
        private String spotifyId;
        private Album album;
        private Artist[] artists;
        private String name;
        private Integer popularity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Album {
        @JsonProperty("id")
        private String spotifyId;
        private String name;

        @JsonProperty("release_date")
        private String releaseDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Artist {
        @JsonProperty("id")
        private String spotifyId;
        private String name;
    }
}
