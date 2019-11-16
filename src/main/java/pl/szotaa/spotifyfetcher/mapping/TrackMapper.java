package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;
import pl.szotaa.spotifyfetcher.persistence.Album;
import pl.szotaa.spotifyfetcher.persistence.AlbumRepository;
import pl.szotaa.spotifyfetcher.persistence.Artist;
import pl.szotaa.spotifyfetcher.persistence.ArtistRepository;
import pl.szotaa.spotifyfetcher.persistence.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TrackMapper {

    @Autowired
    protected AlbumRepository albumRepository;

    @Autowired
    protected ArtistRepository artistRepository;

    @Autowired
    protected AlbumMapper albumMapper;

    @Autowired
    protected ArtistMapper artistMapper;

    public abstract Track toEntity(SpotifyRecentlyPlayedResponse.Track track);

    protected Album getAlbum(SpotifyRecentlyPlayedResponse.Album album) {
        return albumRepository.findBySpotifyId(album.getSpotifyId()).orElseGet(() -> albumRepository.save(albumMapper.toEntity(album)));
    }

    @IterableMapping
    protected Set<Artist> getArtists(SpotifyRecentlyPlayedResponse.Artist[] artists) {
        var artistList = new ArrayList<>(Arrays.asList(artists));
        var spotifyIds = artistList.stream().map(SpotifyRecentlyPlayedResponse.Artist::getSpotifyId).collect(Collectors.toList());
        var allBySpotifyIds = artistRepository.findAllBySpotifyIdIn(spotifyIds);
        if(allBySpotifyIds.size() < artistList.size() ) {
            var foundSpotifyIds = allBySpotifyIds.stream().map(Artist::getSpotifyId).collect(Collectors.toList());
            artistList.stream()
                    .filter(artist -> !foundSpotifyIds.contains(artist.getSpotifyId()))
                    .map(artistMapper::toEntity)
                    .map(this::save)
                    .forEach(allBySpotifyIds::add);
        }
        return new HashSet<>(allBySpotifyIds);
    }

    private Artist save(Artist artist) {
        return artistRepository.findBySpotifyId(artist.getSpotifyId()).orElseGet(() -> artistRepository.save(artist));
    }
}
