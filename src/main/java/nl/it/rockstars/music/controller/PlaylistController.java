package nl.it.rockstars.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.outbound.CreatePlaylistRequest;
import nl.it.rockstars.music.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService service;

    @PostMapping
    ResponseEntity<Long> createPlaylist(Principal principal,
            @RequestBody @Valid @NotNull CreatePlaylistRequest playlistRequest) {

        return ResponseEntity.ok(service.createPlaylist(principal.getName(),
                                                        playlistRequest.getTitle(),
                                                        playlistRequest.getSonglist()));
    }

}
