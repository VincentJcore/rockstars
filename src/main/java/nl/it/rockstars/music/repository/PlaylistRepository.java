package nl.it.rockstars.music.repository;

import nl.it.rockstars.music.repository.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
}
