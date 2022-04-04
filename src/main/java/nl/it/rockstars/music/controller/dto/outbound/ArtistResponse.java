package nl.it.rockstars.music.controller.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArtistResponse {

    @JsonProperty("Id")
    Long id;

    @JsonProperty("Name")
    String name;
}