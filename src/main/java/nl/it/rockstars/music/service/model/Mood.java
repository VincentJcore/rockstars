package nl.it.rockstars.music.service.model;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class Mood {

    @NotBlank
    String genre;
    @NotNull @Min(1)
    Integer belowBpm;
    @NotNull @Min(1)
    Integer aboveBpm;
}
