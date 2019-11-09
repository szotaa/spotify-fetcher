package pl.szotaa.spotifyfetcher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szotaa.spotifyfetcher.persistence.TrackListen;
import pl.szotaa.spotifyfetcher.persistence.TrackListenRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TrackListenServiceImpl implements TrackListenService {

    private final TrackListenRepository repository;

    @Override
    public void saveAll(Collection<TrackListen> trackListens) {
        repository.flush();
        for(var trackListen: trackListens) {
            if(!repository.existsByPlayedAt(trackListen.getPlayedAt())) {
                repository.save(trackListen);
            }
        }
    }
}
