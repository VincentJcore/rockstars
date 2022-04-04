package nl.it.rockstars.music.controller.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CreateArtistRequest{

    @NotNull
    @JsonProperty("Id")
    Long id;

    @NotBlank
    @JsonProperty("Name")
    String name;
}