package nl.it.rockstars.music.controller.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class UpdateSongRequest {

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