package nl.it.rockstars.music.repository;

import nl.it.rockstars.music.repository.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
}
