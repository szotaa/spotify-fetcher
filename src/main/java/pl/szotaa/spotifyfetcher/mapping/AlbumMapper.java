package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.Mapper;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;
import pl.szotaa.spotifyfetcher.persistence.Album;

@Mapper(componentModel = "spring")
public abstract class AlbumMapper {
    public abstract Album toEntity(SpotifyRecentlyPlayedResponse.Album dto);
}
