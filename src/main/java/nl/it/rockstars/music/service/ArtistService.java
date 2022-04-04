package nl.it.rockstars.music.service;

import lombok.RequiredArgsConstructor;
import nl.it.rockstars.music.repository.ArtistRepository;
import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.service.model.Artist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;
    private final ArtistEntityTransformer entityTransformer;

    @Transactional
    public List<Artist> saveAll(List<Artist> artists) {

        final List<ArtistEntity> unsavedEntities = artists.stream()
                                                          .map(entityTransformer::entityFromModel)
                .filter(a -> !(repository.existsById(a.getId()) ||
                        repository.existsArtistEntitiesByName(a.getName())))
                                                          .toList();

        return repository.saveAllAndFlush(unsavedEntities)
                         .stream()
                         .map(entityTransformer::modelFromEntity)
                         .toList();
    }
}
