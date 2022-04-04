package nl.it.rockstars.music.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.music.controller.dto.SongTransformer;
import nl.it.rockstars.music.service.SongService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/song")
@RequiredArgsConstructor
public class SongController {

    private final SongTransformer transformer;
    private final SongService service;

}
