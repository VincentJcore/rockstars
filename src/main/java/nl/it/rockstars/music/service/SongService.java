package nl.it.rockstars.music.service;

import lombok.RequiredArgsConstructor;
import nl.it.rockstars.music.repository.ArtistRepository;
import nl.it.rockstars.music.repository.SongRepository;
import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.repository.entity.SongEntity;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final SongEntityTransformer entityTransformer;

    @Transactional
    public List<Song> saveAll(List<Song> unsavedSongsByMetalArtistsBefore2016) {

        final Set<String> artistNames = unsavedSongsByMetalArtistsBefore2016.stream().map(Song::getArtist).collect(
                Collectors.toUnmodifiableSet());

        Map<String, ArtistEntity> artistEntityByName = artistNames.stream().map(artistRepository::findByName)
                                                                  .filter(Optional::isPresent)
                                                                  .map(Optional::get)
                                                                  .collect(Collectors.toMap(ArtistEntity::getName, e -> e));

        final List<SongEntity> unsavedSongs = new ArrayList<>();

        for(var song: unsavedSongsByMetalArtistsBefore2016) {

            final var maybeArtist = artistEntityByName.get(song.getArtist());

            if(maybeArtist == null) {
                continue;
            }

            if(songRepository.existsById(song.getId())) {
                continue;
            }

            final var entity = entityTransformer.entityFromModel(song, maybeArtist);

            unsavedSongs.add(entity);
        }

        return songRepository.saveAll(unsavedSongs).stream().map(entityTransformer::modelFromCreateRequest).toList();
    }
}
