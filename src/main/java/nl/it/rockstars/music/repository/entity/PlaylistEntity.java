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
import java.util.List;

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

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<PlaylistSongEntity> playlistSongs = new ArrayList<>();

    public void addPlaylistSong(PlaylistSongEntity playlistSong) {
        playlistSongs.add(playlistSong);
        playlistSong.setPlaylist(this);
    }

    public void removePlaylistSong(PlaylistSongEntity classRoomSeat) {
        playlistSongs.remove(classRoomSeat);
        classRoomSeat.setPlaylist(null);
    }
}
