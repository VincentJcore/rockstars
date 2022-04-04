package nl.it.rockstars.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.ArtistTransformer;
import nl.it.rockstars.music.controller.dto.inbound.CreateArtistRequest;
import nl.it.rockstars.music.controller.dto.inbound.UpdateArtistRequest;
import nl.it.rockstars.music.controller.dto.outbound.ArtistResponse;
import nl.it.rockstars.music.service.ArtistService;
import nl.it.rockstars.music.service.model.Artist;
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
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistTransformer transformer;
    private final ArtistService service;

    @GetMapping("/{id}")
    ResponseEntity<ArtistResponse> findArtistById(@PathVariable(name = "id") @NotNull @Min(1) Long id) {

        final Optional<ArtistResponse> maybeArtist = service.findById(id)
                                                            .map(transformer::responseFromModel);

        return ResponseEntity.of(maybeArtist);
    }

    @PostMapping
    ResponseEntity<ArtistResponse> findArtistById(@RequestBody CreateArtistRequest createArtistRequest) {

        final var unsavedArtist = transformer.modelFromCreateRequest(createArtistRequest);

        final Optional<ArtistResponse> artistResult = service.save(unsavedArtist)
                                                             .map(transformer::responseFromModel);

        // TODO: create clean mapping of custom exception instead of abusing optional
        if(artistResult.isPresent()) {
            return ResponseEntity.of(artistResult);
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<ArtistResponse> updateArtistById(@PathVariable(name = "id") @NotNull @Min(1) Long id, @RequestBody UpdateArtistRequest updateArtistRequest) {

        final Optional<Artist> maybeArtist = service.findById(id);

        if(maybeArtist.isEmpty()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        // TODO: create clean mapping of custom exception instead of abusing optional
        final Optional<ArtistResponse> maybeUpdatedArtist =
                service.updateById(id, updateArtistRequest.getName())
                       .map(transformer::responseFromModel);

        if(maybeUpdatedArtist.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.of(maybeUpdatedArtist);
    }

    @DeleteMapping("/{id}")
    void deleteArtistById(@PathVariable(name = "id") @NotNull @Min(1) Long id) {

        service.deleteById(id);
    }

}
