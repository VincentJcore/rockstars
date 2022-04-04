package nl.it.rockstars.music.controller.dto;

import nl.it.rockstars.music.controller.dto.inbound.CreateArtistRequest;
import nl.it.rockstars.music.service.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistTransformer {

    public Artist modelFromCreateRequest(CreateArtistRequest request) {
        return Artist.builder()
                     .id(request.getId())
                     .name(request.getName())
                     .build();
    }

}
