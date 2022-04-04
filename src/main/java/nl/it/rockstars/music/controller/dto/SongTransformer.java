package nl.it.rockstars.music.controller.dto;

import nl.it.rockstars.music.controller.dto.inbound.CreateSongRequest;
import nl.it.rockstars.music.controller.dto.inbound.UpdateSongRequest;
import nl.it.rockstars.music.controller.dto.outbound.SongResponse;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongTransformer {

    public Song updatedModelFromUpdateRequest(Song original, UpdateSongRequest request) {

        return Song.builder()
                   .id(original.getId())
                   .artist(original.getArtist())
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

    public SongResponse responseFromModel(Song model) {

        return SongResponse.builder()
                   .id(model.getId())
                   .artist(model.getArtist())
                   .year(model.getYear())
                   .spotifyId(model.getSpotifyId())
                   .album(model.getAlbum())
                   .duration(model.getDuration())
                   .shortname(model.getShortname())
                   .genre(model.getGenre())
                   .bpm(model.getBpm())
                   .name(model.getName())
                   .build();
    }
}
