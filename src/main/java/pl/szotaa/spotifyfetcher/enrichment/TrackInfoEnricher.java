package pl.szotaa.spotifyfetcher.enrichment;

import pl.szotaa.spotifyfetcher.persistence.Track;

import java.util.Collection;

public interface TrackInfoEnricher {
    Collection<Track> enrich(Collection<Track> tracks);
    Track enrich(Track track);
}
