package pl.szotaa.spotifyfetcher.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface SpotifyRepository<T extends SpotifyIdentifiable> extends JpaRepository<T, String> {
    Optional<T> findBySpotifyId(String spotifyId);
    Collection<T> findAllBySpotifyIdIn(Collection<String> ids);
}
