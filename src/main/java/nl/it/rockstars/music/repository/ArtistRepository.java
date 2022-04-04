package nl.it.rockstars.music.repository;

import nl.it.rockstars.music.repository.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    Optional<ArtistEntity> findByName(String name);

    boolean existsArtistEntitiesByNameOrId(String name, Long id);
}
