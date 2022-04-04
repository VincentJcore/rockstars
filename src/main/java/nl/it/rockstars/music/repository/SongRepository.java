package nl.it.rockstars.music.repository;

import nl.it.rockstars.music.repository.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findSongEntitiesByAlbum(String album);

    List<SongEntity> findAllByAlbum(String album);

    List<SongEntity> findAllByGenreAndBpmGreaterThanAndBpmLessThan(String genre, int belowBpm, int aboveBpm);
}
