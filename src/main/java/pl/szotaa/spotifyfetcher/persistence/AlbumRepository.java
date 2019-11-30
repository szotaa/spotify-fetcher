package pl.szotaa.spotifyfetcher.persistence;

import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AlbumRepository extends SpotifyRepository<Album> {

    @Query(
            value = "SELECT * FROM album a LEFT JOIN album_genre ag on a.id = ag.album_id WHERE ag.album_id IS NULL",
            nativeQuery = true
    )
    Collection<Album> findAlbumsToBeEnriched();
}
