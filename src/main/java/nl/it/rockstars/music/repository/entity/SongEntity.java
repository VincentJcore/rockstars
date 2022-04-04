package nl.it.rockstars.music.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "song", schema = "rockstars")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SongEntity {

    @Id
    private Long id;
    private int year;
    private String spotifyId;
    private String album;
    private int duration;
    private String shortname;
    private String genre;
    private int bpm;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ArtistEntity artist;
}
