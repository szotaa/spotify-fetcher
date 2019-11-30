package pl.szotaa.spotifyfetcher.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.szotaa.spotifyfetcher.enrichment.AlbumInfoEnricher;
import pl.szotaa.spotifyfetcher.persistence.AlbumRepository;

@Component
@RequiredArgsConstructor
public class FetchAlbumInfoScheduledJob implements ScheduledJob {

    private static final String EVERY_6_HOURS_AT_THREE_QUARTERS_PAST_CRON_TAB = "0 45 */6 * * *";
    private final AlbumInfoEnricher enricher;
    private final AlbumRepository repository;

    @Override
    @Scheduled(cron = EVERY_6_HOURS_AT_THREE_QUARTERS_PAST_CRON_TAB)
    public void execute() {
        var top100ByGenresIsNull = repository.findAlbumsToBeEnriched();
        top100ByGenresIsNull.forEach(album -> repository.save(enricher.enrich(album)));
    }
}
