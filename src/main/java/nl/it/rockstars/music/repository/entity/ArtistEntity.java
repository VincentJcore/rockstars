package nl.it.rockstars.music.repository.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist", schema = "rockstars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ArtistEntity {

    @Id
    @Column(unique = true, nullable = false, name = "id")
    private Long id;
    @Column(unique = true, nullable = false, name = "name")
    private String name;

    @OneToMany(
            mappedBy = "artist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    private List<SongEntity> songs = new ArrayList<>();

    public void addPet(SongEntity song) {
        songs.add(song);
        song.setArtist(this);
    }

    public void removePet(SongEntity song) {
        songs.remove(song);
        song.setArtist(null);
    }
}