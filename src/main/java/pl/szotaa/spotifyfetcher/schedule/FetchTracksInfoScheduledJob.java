package pl.szotaa.spotifyfetcher.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.szotaa.spotifyfetcher.enrichment.TrackInfoEnricher;
import pl.szotaa.spotifyfetcher.persistence.Track;
import pl.szotaa.spotifyfetcher.persistence.TrackRepository;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class FetchTracksInfoScheduledJob implements ScheduledJob {

    private static final String EVERY_6_HOURS_AT_HALF_PAST_CRON_TAB = "0 30 */6 * * *";

    private final TrackRepository trackRepository;
    private final TrackInfoEnricher trackInfoEnricher;

    @Override
    @Scheduled(cron = EVERY_6_HOURS_AT_HALF_PAST_CRON_TAB)
    public void execute() {
        var tracks = trackRepository.findTop100ByTrackInfoIsNull();
        Collection<Track> enriched = trackInfoEnricher.enrich(tracks);
        enriched.forEach(trackRepository::save);
    }
}
