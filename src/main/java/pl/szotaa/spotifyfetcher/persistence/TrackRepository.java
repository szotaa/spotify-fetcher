package pl.szotaa.spotifyfetcher.persistence;

import java.util.Collection;

public interface TrackRepository extends SpotifyRepository<Track> {
    Collection<Track> findTop100ByTrackInfoIsNull();
}
