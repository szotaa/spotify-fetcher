package pl.szotaa.spotifyfetcher.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Track implements SpotifyIdentifiable {

    @Id
    @GeneratedValue
    private UUID id;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "spotify_id", unique = true)
    private String spotifyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Album album;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "track_artist",
            joinColumns = @JoinColumn(name = "track_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    private Set<Artist> artists;

    @NotNull
    private String name;

    private Integer popularity;

    @Embedded
    private TrackInfo trackInfo;
}
