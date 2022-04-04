package nl.it.rockstars.music.repository.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlist", schema = "rockstars")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creator;
    private String title;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PlaylistSongEntity> playlistSongs = new HashSet<>();

    public void addPlaylistSong(PlaylistSongEntity playlistSong) {
        playlistSongs.add(playlistSong);
        playlistSong.setPlaylist(this);
    }

    public void removePlaylistSong(PlaylistSongEntity classRoomSeat) {
        playlistSongs.remove(classRoomSeat);
        classRoomSeat.setPlaylist(null);
    }
}
