package nl.it.rockstars.music.controller.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CreateSongRequest{

	@NotNull @Min(1)
	@JsonProperty("Id")
	Long id;

	@NotBlank
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