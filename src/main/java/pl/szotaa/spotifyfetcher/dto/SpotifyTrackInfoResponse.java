package pl.szotaa.spotifyfetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyTrackInfoResponse {
    @JsonProperty("duration_ms")
    private double durationMs;
    private double key;
    private double mode;
    @JsonProperty("time_signature")
    private double timeSignature;
    private double acousticness;
    private double danceability;
    private double energy;
    private double instrumentalness;
    private double liveness;
    private double loudness;
    private double speechiness;
    private double valence;
    private double tempo;
}
