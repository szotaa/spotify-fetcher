package pl.szotaa.spotifyfetcher.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Artist implements SpotifyIdentifiable {

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

    @NotNull
    private String name;
}
