package nl.it.rockstars.music.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "playlist_song", schema = "rockstars")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlaylistSongEntity {

    @EmbeddedId
    private PlaylistSongEntityKey id = new PlaylistSongEntityKey();

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "playlist_id")
    @EqualsAndHashCode.Exclude
    private PlaylistEntity playlist;

    @ManyToOne
    @MapsId("songId")
    @JoinColumn(name = "song_id")
    @EqualsAndHashCode.Exclude
    private SongEntity song;

    private Long playlistIndex;
}
