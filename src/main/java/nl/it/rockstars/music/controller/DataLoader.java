package nl.it.rockstars.music.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.ArtistTransformer;
import nl.it.rockstars.music.controller.dto.SongTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreateArtistRequest;
import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.service.ArtistService;
import nl.it.rockstars.music.service.SongService;
import nl.it.rockstars.music.service.model.Artist;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DataLoader {

    static final String ULTIMATE_GENRE = "Metal";
    static final int YEAR_AFTER_METAL = 2016;

    @Value("classpath:data/artists.json")
    private final Resource artistsFile;
    @Value("classpath:data/songs.json")
    private final Resource songsFile;
    private final ObjectMapper objectMapper;
    private final ArtistTransformer artistTransformer;
    private final ArtistService artistService;

    private final SongTransformer songTransformer;
    private final SongService songService;

    @PostConstruct
    void initData() throws IOException {
        loadMetalArtists();
        loadMetalArtistSongs();
    }

    void loadMetalArtistSongs() throws IOException {

        final List<CreateSongRequest> songRequests = objectMapper.readValue(songsFile.getInputStream(),
                                                                            new TypeReference<ArrayList<CreateSongRequest>>() {
                                                                            });

        final Set<String> metalArtistNames =
                songRequests.stream()
                            .filter(e -> e.getGenre().equals(DataLoader.ULTIMATE_GENRE))
                            .map(CreateSongRequest::getArtist)
                            .collect(Collectors.toUnmodifiableSet());

        final List<Song> unsavedSongsByMetalArtistsBefore2016 =
                songRequests.stream()
                            .filter(e -> metalArtistNames.contains(e.getArtist()))
                            .filter(e -> e.getYear() < DataLoader.YEAR_AFTER_METAL)
                            .map(songTransformer::modelFromCreateRequest).toList();

        try {
            final List<Song> savedSongs = songService.saveAll(unsavedSongsByMetalArtistsBefore2016);
            log.info("METAL SONGS SAVED, N={}", savedSongs.size());
        } catch (Exception e) {
            log.warn("EXCEPTION DURING SONGS SAVING", e);
        }

    }

    void loadMetalArtists() throws IOException {

        final Set<String> metalArtistNames = objectMapper.readValue(songsFile.getInputStream(),
                                                                    new TypeReference<ArrayList<CreateSongRequest>>() {
                                                                    }).stream()
                                                         .filter(e -> e.getGenre().equals(DataLoader.ULTIMATE_GENRE))
                                                         .map(CreateSongRequest::getArtist).collect(Collectors.toUnmodifiableSet());

        final List<Artist> unsavedArtists = objectMapper.readValue(artistsFile.getInputStream(),
                                                                   new TypeReference<ArrayList<CreateArtistRequest>>() {
                                                                   })
                                                        .stream()
                                                        .filter(e -> metalArtistNames.contains(e.getName()))
                                                        .map(artistTransformer::modelFromCreateRequest)
                                                        .toList();
        try {
            final List<Artist> savedArtists = artistService.saveAll(unsavedArtists);
            log.info("METAL ARTISTS SAVED, N={}", savedArtists.size());
        } catch (Exception e) {
            log.warn("EXCEPTION DURING ARTIST SAVING", e);
        }

    }

}
