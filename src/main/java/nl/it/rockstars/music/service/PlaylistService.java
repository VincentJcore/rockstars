package nl.it.rockstars.music.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.repository.PlaylistRepository;
import nl.it.rockstars.music.repository.SongRepository;
import nl.it.rockstars.music.repository.entity.PlaylistEntity;
import nl.it.rockstars.music.repository.entity.PlaylistSongEntity;
import nl.it.rockstars.music.repository.entity.SongEntity;
import nl.it.rockstars.music.service.model.Mood;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

        final var savedPlaylist = playlistRepository.saveAndFlush(unsavedPlaylist);

        final var songs = songRepository.findAllById(songlist);

        for(int i = 0; i < songs.size(); i++) {

            final var playlistSong = new PlaylistSongEntity();
            playlistSong.setSong(songs.get(i));
            playlistSong.setPlaylistIndex((long) i);
            playlistSong.setPlaylist(savedPlaylist);
            savedPlaylist.addPlaylistSong(playlistSong);
        }

        final var result = playlistRepository.saveAndFlush(savedPlaylist).getId();

        return result;
    }

    @Transactional
    public Optional<Mood> extractMood(Long id) {

        final var playList = playlistRepository.findById(id);

        if(playList.isEmpty()) {
            return Optional.empty();
        }

        final List<SongEntity> songs = playList.get().getPlaylistSongs().stream().map(e -> e.getSong()).toList();

        final var belowBpm = songs.stream().map(SongEntity::getBpm).max(Comparator.naturalOrder()).orElse(1);
        final var aboveBpm = songs.stream().map(SongEntity::getBpm).min(Comparator.naturalOrder()).orElse(0);
        final List<String> genres = songs.stream().map(SongEntity::getGenre).toList();
        final String randomGenre = genres.get(new Random().nextInt(genres.size()));

        return Optional.of(new Mood(randomGenre, belowBpm, aboveBpm));
    }
}
