package nl.it.rockstars.music.service.model;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Artist {

    Long id;
    String name;
}