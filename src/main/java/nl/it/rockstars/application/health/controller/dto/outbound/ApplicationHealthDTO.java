package nl.it.rockstars.application.health.controller.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import nl.it.rockstars.application.health.service.model.ApplicationHealth;

@Value
public class ApplicationHealthDTO {

	@JsonProperty("application_health")
	private final ApplicationHealth applicationHealth;

}
