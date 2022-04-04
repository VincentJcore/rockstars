package nl.it.rockstars.music.service;

import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.repository.entity.SongEntity;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SongEntityTransformer {

    @Transactional
    SongEntity entityFromModel(Song model, ArtistEntity artistEntity) {

        final var entity = new SongEntity();
        entity.setId(model.getId());
        entity.setYear(model.getYear());
        entity.setSpotifyId(model.getSpotifyId());
        entity.setAlbum(model.getAlbum());
        entity.setDuration(model.getDuration());
        entity.setShortname(model.getShortname());
        entity.setGenre(model.getGenre());
        entity.setBpm(model.getBpm());
        entity.setName(model.getName());

        entity.setArtist(artistEntity);

        return entity;
    }

    @Transactional
    public Song modelFromCreateRequest(SongEntity model) {

        return Song.builder()
                   .id(model.getId())
                   .artist(model.getArtist().getName())
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
