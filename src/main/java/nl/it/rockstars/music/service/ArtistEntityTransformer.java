package nl.it.rockstars.music.service;

import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.service.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistEntityTransformer {

    public ArtistEntity entityFromModel(Artist model) {

        final var entity = new ArtistEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());

        return entity;
    }

    public Artist modelFromEntity(ArtistEntity entity) {

        return Artist.builder()
                .id(entity.getId())
                .name(entity.getName())
                     .build();
    }
}
