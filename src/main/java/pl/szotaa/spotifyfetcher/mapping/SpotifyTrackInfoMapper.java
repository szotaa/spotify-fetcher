package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.szotaa.spotifyfetcher.dto.SpotifyTrackInfoResponse;
import pl.szotaa.spotifyfetcher.persistence.Modality;
import pl.szotaa.spotifyfetcher.persistence.TrackInfo;

@Mapper(componentModel = "spring")
public interface SpotifyTrackInfoMapper {

    @Mappings({
            @Mapping(source = "mode", target = "mode"),
            @Mapping(source = "durationMs", target = "duration")
    })
    TrackInfo toEntity(SpotifyTrackInfoResponse dto);

    default Modality getModality(Double ordinal) {
        return Modality.values()[ordinal.intValue()];
    }
}
