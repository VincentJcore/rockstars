package nl.it.rockstars.music.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.SongTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreateArtistRequest;
import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.service.SongService;
import nl.it.rockstars.music.service.model.Artist;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/song")
@RequiredArgsConstructor
public class SongController {

    @Value("classpath:data/songs.json")
    private final Resource songsFile;
    private final ObjectMapper objectMapper;
    private final SongTransformer transformer;
    private final SongService service;

    @PostConstruct
    void loadMetalArtistSongs() throws IOException {

        final List<CreateSongRequest> songRequests = objectMapper.readValue(songsFile.getInputStream(),
                                                                            new TypeReference<ArrayList<CreateSongRequest>>() {
                                                                            });

        final Set<String> metalArtistNames =
                songRequests.stream()
                            .filter(e -> e.getGenre().equals(ArtistController.ULTIMATE_GENRE))
                            .map(CreateSongRequest::getArtist)
                            .collect(Collectors.toUnmodifiableSet());

        final List<Song> unsavedSongsByMetalArtistsBefore2016 =
                songRequests.stream()
                            .filter(e -> metalArtistNames.contains(e.getArtist()))
                            .filter(e -> e.getYear() < ArtistController.YEAR_AFTER_METAL)
                            .map(transformer::modelFromCreateRequest).toList();

        try {
            final List<Song> savedSongs = service.saveAll(unsavedSongsByMetalArtistsBefore2016);
            log.info("METAL SONGS SAVED, N={}", savedSongs.size());
        } catch (Exception e) {
            log.warn("EXCEPTION DURING SONGS SAVING", e);
        }

    }


}
