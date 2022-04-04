package nl.it.rockstars.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.SongTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.controller.dto.inbound.UpdateArtistRequest;
import nl.it.rockstars.music.controller.dto.inbound.UpdateSongRequest;
import nl.it.rockstars.music.controller.dto.outbound.SongResponse;
import nl.it.rockstars.music.service.SongService;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/song")
@RequiredArgsConstructor
public class SongController {

    private final SongTransformer transformer;
    private final SongService service;

    @GetMapping("/{id}")
    ResponseEntity<SongResponse> findSongById(@PathVariable(name = "id") @NotNull @Min(1) Long id){

        final Optional<SongResponse> maybeSong = service.findById(id).map(transformer::responseFromModel);

        return ResponseEntity.of(maybeSong);
    }

    @PostMapping
    ResponseEntity<SongResponse> createSong(@RequestBody CreateSongRequest createSongRequest) {

        final var unsavedSong = transformer.modelFromCreateRequest(createSongRequest);

        // TODO: create clean mapping of custom exception instead of abusing optional
        final Optional<SongResponse> songResult = service.save(unsavedSong)
                                                         .map(transformer::responseFromModel);

        if(songResult.isPresent()) {
            return ResponseEntity.of(songResult);
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<SongResponse> updateSongById(@PathVariable(name = "id") @NotNull @Min(1) Long id,
            @RequestBody UpdateSongRequest updateSongRequest) {

        final Optional<Song> maybeSong = service.findById(id);

        if(maybeSong.isEmpty()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        final Song updatedModel = transformer.updatedModelFromUpdateRequest(maybeSong.get(), updateSongRequest);

        // TODO: create clean mapping of custom exception instead of abusing optional
        final Optional<SongResponse> maybeUpdatedSong = service.updateSong(updatedModel)
                                                               .map(transformer::responseFromModel);

        if(maybeUpdatedSong.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.of(maybeUpdatedSong);
    }

    @DeleteMapping("/{id}")
    void deleteSongById(@PathVariable(name = "id") @NotNull @Min(1) Long id) {

        service.deleteById(id);
    }

}
