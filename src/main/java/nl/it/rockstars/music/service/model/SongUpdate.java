package nl.it.rockstars.music.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SongUpdate {

    int year;
    String spotifyId;
    String album;
    int duration;
    String shortname;
    String genre;
    int bpm;
    String name;
}
