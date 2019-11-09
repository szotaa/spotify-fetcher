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
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class TrackListen {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    private Track track;

    @NotNull
    @Column(name = "played_at")
    private LocalDateTime playedAt;
}
