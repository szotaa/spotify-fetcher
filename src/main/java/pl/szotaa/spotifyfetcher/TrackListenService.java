package pl.szotaa.spotifyfetcher;

import pl.szotaa.spotifyfetcher.persistence.TrackListen;

import java.util.Collection;

public interface TrackListenService {
    void saveAll(Collection<TrackListen> trackListens);
}
