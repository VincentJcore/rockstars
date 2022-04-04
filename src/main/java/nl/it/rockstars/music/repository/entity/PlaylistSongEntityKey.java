package nl.it.rockstars.music.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlaylistSongEntityKey implements Serializable {

    @Column(name = "playlist_id")
    private Long playlistId;

    @Column(name = "song_id")
    private Long songId;
}
