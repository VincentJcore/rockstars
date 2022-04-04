SET SCHEMA 'rockstars';

CREATE TABLE playlist (
	id BIGSERIAL PRIMARY KEY,
	title varchar NOT NULL,
	creator varchar NOT NULL
);

CREATE TABLE playlist_song (
	playlist_id BIGINT NOT NULL,
	song_id BIGINT NOT NULL,
	playlist_index BIGINT NOT NULL,
	CONSTRAINT fk_song
      FOREIGN KEY(song_id)
	  	REFERENCES song(id),
	CONSTRAINT fk_playlist
      FOREIGN KEY(playlist_id)
	  	REFERENCES playlist(id),
	PRIMARY KEY(playlist_id, song_id)
);

