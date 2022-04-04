package nl.it.rockstars.music.service.transformer;

import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.repository.entity.SongEntity;
import nl.it.rockstars.music.service.model.Song;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SongEntityTransformer {

    @Transactional
    public SongEntity entityFromModel(Song model, ArtistEntity artistEntity) {

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
    public Song modelFromEntity(SongEntity entity) {

        return Song.builder()
                   .id(entity.getId())
                   .artist(entity.getArtist().getName())
                   .year(entity.getYear())
                   .spotifyId(entity.getSpotifyId())
                   .album(entity.getAlbum())
                   .duration(entity.getDuration())
                   .shortname(entity.getShortname())
                   .genre(entity.getGenre())
                   .bpm(entity.getBpm())
                   .name(entity.getName())
                   .build();
    }

    @Transactional
    public SongEntity updateEntityFromModel(SongEntity entity, Song model) {

        if (model.getYear() != 0) {
            entity.setYear(model.getYear());
        }

        if (model.getSpotifyId() != null) {
            entity.setSpotifyId(model.getSpotifyId());
        }

        if (model.getAlbum() != null) {
            entity.setAlbum(model.getAlbum());
        }

        if (model.getDuration() != 0) {
            entity.setDuration(model.getDuration());
        }

        if (model.getShortname() != null) {
            entity.setShortname(model.getShortname());
        }

        if (model.getGenre() != null) {
            entity.setGenre(model.getGenre());
        }

        if (model.getBpm() != 0) {
            entity.setBpm(model.getBpm());
        }

        if (model.getName() != null) {
            entity.setName(model.getName());
        }

        return entity;

    }

}
