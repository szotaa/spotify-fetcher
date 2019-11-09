package pl.szotaa.spotifyfetcher.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TrackListenRepository extends JpaRepository<TrackListen, UUID> {
    boolean existsByPlayedAt(LocalDateTime playedAt);
}
