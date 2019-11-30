package pl.szotaa.spotifyfetcher.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AlbumGenre {
//    @Column(name = "album_id")
//    private UUID albumId;
    private String name;
}
