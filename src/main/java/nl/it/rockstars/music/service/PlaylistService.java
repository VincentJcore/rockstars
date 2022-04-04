package nl.it.rockstars.music.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.repository.PlaylistRepository;
import nl.it.rockstars.music.repository.SongRepository;
import nl.it.rockstars.music.repository.entity.PlaylistEntity;
import nl.it.rockstars.music.repository.entity.PlaylistSongEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    @Transactional
    public Long createPlaylist(String creator, String title, List<Long> songlist) {

        final var unsavedPlaylist = new PlaylistEntity();
        unsavedPlaylist.setCreator(creator);
        unsavedPlaylist.setTitle(title);

        final var savedPlaylist = playlistRepository.save(unsavedPlaylist);

        final var songs = songRepository.findAllById(songlist);

        for(int i = 0; i < songs.size(); i++) {

            final var playlistSong = new PlaylistSongEntity();
            playlistSong.setSong(songs.get(i));
            playlistSong.setPlaylistIndex((long) i);
            savedPlaylist.addPlaylistSong(playlistSong);
        }

        return playlistRepository.save(savedPlaylist).getId();
    }
}
