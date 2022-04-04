package nl.it.rockstars.music.controller.dto.inbound;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class FindAlbumRequest {

    @NotBlank
    String albumName;

}
