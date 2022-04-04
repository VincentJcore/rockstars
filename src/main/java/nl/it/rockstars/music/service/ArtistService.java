package nl.it.rockstars.music.service;

import lombok.RequiredArgsConstructor;
import nl.it.rockstars.music.repository.ArtistRepository;
import nl.it.rockstars.music.repository.entity.ArtistEntity;
import nl.it.rockstars.music.service.model.Artist;
import nl.it.rockstars.music.service.transformer.ArtistEntityTransformer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;
    private final ArtistEntityTransformer entityTransformer;

    @Transactional
    public List<Artist> saveAll(List<Artist> artists) {

        final List<ArtistEntity> unsavedEntities = artists.stream()
                                                          .map(entityTransformer::entityFromModel)
                .filter(a -> !(repository.existsArtistEntitiesByNameOrId(a.getName(), a.getId())))
                                                          .toList();

        return repository.saveAllAndFlush(unsavedEntities)
                         .stream()
                         .map(entityTransformer::modelFromEntity)
                         .toList();
    }

    public Optional<Artist> findById(Long id) {

        return repository.findById(id)
                         .map(entityTransformer::modelFromEntity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<Artist> save(Artist unsavedArtist) {

        return Optional.ofNullable(unsavedArtist)
                .filter(e -> !repository.existsArtistEntitiesByNameOrId(unsavedArtist.getName(), unsavedArtist.getId()))
                .map(entityTransformer::entityFromModel)
                .map(repository::save)
                .map(entityTransformer::modelFromEntity);
    }

    public Optional<Artist> updateById(Long id, String name) {

        final Optional<ArtistEntity> artistExistsWithNewName = repository.findByName(name)
                                                                  .filter(e -> e.getId() != id);

        if(artistExistsWithNewName.isPresent()) {
            Optional.empty();
        }

        return repository.findById(id)
                         .map(e -> {
                             e.setName(name);
                             return e;
                         })
                         .map(repository::save)
                         .map(entityTransformer::modelFromEntity);

    }
}
