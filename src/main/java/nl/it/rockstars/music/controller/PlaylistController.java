package nl.it.rockstars.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.transformer.SongTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreatePlaylistRequest;
import nl.it.rockstars.music.controller.dto.outbound.SongResponse;
import nl.it.rockstars.music.service.PlaylistService;
import nl.it.rockstars.music.service.SongService;
import nl.it.rockstars.music.service.model.Mood;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService service;
    private final SongService songService;
    private final SongTransformer songTransformer;

    @PostMapping
    ResponseEntity<Long> createPlaylist(@RequestBody @Valid @NotNull CreatePlaylistRequest playlistRequest) {

        return ResponseEntity.ok(service.createPlaylist("owner",
                                                        playlistRequest.getTitle(),
                                                        playlistRequest.getSonglist()));
    }

    @GetMapping("/{id}/recommendation")
    ResponseEntity<List<SongResponse>> findRecommendation(@PathVariable(name = "id") Long id) {

        final Optional<Mood> maybeMood = service.extractMood(id);

        if(maybeMood.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        final Mood mood = maybeMood.get();

        final List<SongResponse> moodSongs = songService.findByMood(mood.getGenre(), mood.getBelowBpm(),
                                                                    mood.getAboveBpm())
                                                        .stream()
                                                        .map(songTransformer::responseFromModel)
                                                        .toList();

        return ResponseEntity.ok(moodSongs);
    }

}
