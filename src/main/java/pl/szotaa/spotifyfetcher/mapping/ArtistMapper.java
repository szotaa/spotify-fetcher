package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.Mapper;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;
import pl.szotaa.spotifyfetcher.persistence.Artist;

@Mapper(componentModel = "spring")
public abstract class ArtistMapper {
    public abstract Artist toEntity(SpotifyRecentlyPlayedResponse.Artist dto);
}
