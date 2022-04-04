SET SCHEMA 'rockstars';

CREATE TABLE artist (
	id BIGINT PRIMARY KEY,
	name varchar NOT NULL
);

CREATE TABLE song (
	id BIGINT PRIMARY KEY,
	name varchar NOT NULL,
	year INT,
	duration INT,
	bpm INT,
	spotify_id varchar,
	album varchar,
	shortname varchar,
	genre varchar,
	artist_id BIGINT NOT NULL,
	CONSTRAINT fk_artist
      FOREIGN KEY(artist_id)
	  	REFERENCES artist(id)
);

