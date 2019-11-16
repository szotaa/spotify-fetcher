package pl.szotaa.spotifyfetcher.enrichment;

import pl.szotaa.spotifyfetcher.persistence.Track;

import java.util.Collection;

public interface TrackInfoEnricher extends Enricher<Track> {
    Collection<Track> enrich(Collection<Track> tracks);
}
