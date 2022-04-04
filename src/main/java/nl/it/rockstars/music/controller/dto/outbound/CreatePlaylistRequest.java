package nl.it.rockstars.music.controller.dto.outbound;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.List;

@Value
public class CreatePlaylistRequest {

    @NotBlank
    String title;
    @NotEmpty
    List<Long> songlist;

}
