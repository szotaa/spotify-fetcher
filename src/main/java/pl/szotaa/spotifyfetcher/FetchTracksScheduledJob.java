package pl.szotaa.spotifyfetcher;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.szotaa.spotifyfetcher.mapping.ItemMapper;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class FetchTracksScheduledJob implements ScheduledJob {

    private static final String EVERY_THREE_HOURS_CRON_EXPRESSION = "0 0 */3 * * *";

    private final SpotifyAuthService authService;
    private final SpotifyPlayHistoryService playHistoryService;
    private final ItemMapper itemMapper;
    private final TrackListenService trackListenService;

    @Override
    @PostConstruct
    @Scheduled(cron = EVERY_THREE_HOURS_CRON_EXPRESSION)
    public void execute() {
        var accessToken = authService.getAccessToken();
        var recentlyPlayedTracks = playHistoryService.getRecentlyPlayedTracks(accessToken);
        var trackListens = Stream.of(recentlyPlayedTracks.getItems()).map(itemMapper::toEntity).collect(Collectors.toList());
        trackListenService.saveAll(trackListens);
    }
}
