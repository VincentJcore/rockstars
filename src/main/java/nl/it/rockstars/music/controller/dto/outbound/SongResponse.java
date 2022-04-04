package nl.it.rockstars.music.controller.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class SongResponse {

	@JsonProperty("Id")
	Long id;

	@JsonProperty("Artist")
	String artist;

	@JsonProperty("Year")
	int year;

	@JsonProperty("SpotifyId")
	String spotifyId;

	@JsonProperty("Album")
	String album;

	@JsonProperty("Duration")
	int duration;

	@JsonProperty("Shortname")
	String shortname;

	@JsonProperty("Genre")
	String genre;

	@JsonProperty("Bpm")
	int bpm;

	@JsonProperty("Name")
	String name;

}