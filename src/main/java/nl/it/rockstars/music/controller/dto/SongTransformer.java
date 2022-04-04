package nl.it.rockstars.music.controller.dto;

import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongTransformer {

    public Song modelFromCreateRequest(CreateSongRequest request) {

        return Song.builder()
                   .id(request.getId())
                   .artist(request.getArtist())
                   .year(request.getYear())
                   .spotifyId(request.getSpotifyId())
                   .album(request.getAlbum())
                   .duration(request.getDuration())
                   .shortname(request.getShortname())
                   .genre(request.getGenre())
                   .bpm(request.getBpm())
                   .name(request.getName())
                   .build();
    }
}
