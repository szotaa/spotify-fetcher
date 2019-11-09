package pl.szotaa.spotifyfetcher.mapping;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szotaa.spotifyfetcher.dto.SpotifyRecentlyPlayedResponse;
import pl.szotaa.spotifyfetcher.persistence.Track;
import pl.szotaa.spotifyfetcher.persistence.TrackListen;
import pl.szotaa.spotifyfetcher.persistence.TrackRepository;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {

    @Autowired
    protected TrackRepository trackRepository;

    @Autowired
    protected TrackMapper trackMapper;

    public abstract TrackListen toEntity(SpotifyRecentlyPlayedResponse.Item dto);

    protected Track getTrack(SpotifyRecentlyPlayedResponse.Track track) {
        return trackRepository.findBySpotifyId(track.getSpotifyId()).orElseGet(() -> trackRepository.save(trackMapper.toEntity(track)));
    }
}
