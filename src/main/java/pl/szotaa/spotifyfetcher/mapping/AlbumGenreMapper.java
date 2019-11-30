package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.Mapper;
import pl.szotaa.spotifyfetcher.dto.SpotifyAlbumInfoResponse;
import pl.szotaa.spotifyfetcher.persistence.AlbumGenre;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface AlbumGenreMapper {

    default Set<AlbumGenre> toEntities(SpotifyAlbumInfoResponse dto) {
        var genresStream = Objects.nonNull(dto) ? Arrays.stream(dto.getGenres()) : Stream.<String>empty();
        return genresStream.map(
                genre -> {var entity = new AlbumGenre(); entity.setName(genre); return entity;}
        ).collect(Collectors.toSet());
    }
}
