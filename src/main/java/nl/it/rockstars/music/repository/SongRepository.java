package nl.it.rockstars.music.repository;

import nl.it.rockstars.music.repository.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

}
