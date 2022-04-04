package nl.it.rockstars.music.controller.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CreateArtistRequest{

    @JsonProperty("Id")
    Integer id;

    @JsonProperty("Name")
    String name;
}