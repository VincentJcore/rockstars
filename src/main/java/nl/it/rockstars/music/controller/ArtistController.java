package nl.it.rockstars.music.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.ArtistTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreateArtistRequest;
import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.service.ArtistService;
import nl.it.rockstars.music.service.model.Artist;
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
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {

    static final String ULTIMATE_GENRE = "Metal";
    static final int YEAR_AFTER_METAL = 2016;

    @Value("classpath:data/artists.json")
    private final Resource artistsFile;
    @Value("classpath:data/songs.json")
    private final Resource songsFile;
    private final ObjectMapper objectMapper;
    private final ArtistTransformer transformer;
    private final ArtistService service;

    @PostConstruct
    void loadMetalArtists() throws IOException {

        final Set<String> metalArtistNames = objectMapper.readValue(songsFile.getInputStream(),
                                                                    new TypeReference<ArrayList<CreateSongRequest>>() {
                                                                    }).stream()
                                                         .filter(e -> e.getGenre().equals(ArtistController.ULTIMATE_GENRE))
                                                         .map(CreateSongRequest::getArtist).collect(Collectors.toUnmodifiableSet());

        final List<Artist> unsavedArtists = objectMapper.readValue(artistsFile.getInputStream(),
                                                                   new TypeReference<ArrayList<CreateArtistRequest>>() {
                                                                   })
                                                        .stream()
                                                        .filter(e -> metalArtistNames.contains(e.getName()))
                                                        .map(transformer::modelFromCreateRequest)
                                                        .toList();
        try {
            final List<Artist> savedArtists = service.saveAll(unsavedArtists);
            log.info("METAL ARTISTS SAVED, N={}", savedArtists.size());
        } catch (Exception e) {
            log.warn("EXCEPTION DURING ARTIST SAVING", e);
        }

    }


}
