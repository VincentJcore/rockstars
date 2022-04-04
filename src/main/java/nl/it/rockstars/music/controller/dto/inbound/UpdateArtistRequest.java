package nl.it.rockstars.music.controller.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UpdateArtistRequest {

    @NotBlank
    @JsonProperty("Name")
    String name;
}