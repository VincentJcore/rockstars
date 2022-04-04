package nl.it.rockstars.application.health.service;

import lombok.RequiredArgsConstructor;
import nl.it.rockstars.application.health.service.model.ApplicationHealth;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationHealthService {

	private final HealthEndpoint healthEndpoint;

	public ApplicationHealth getCurrentHealth() {

		final var currentHealthStatus = (Status.UP == healthEndpoint.health().getStatus()) ? Status.UP :
										Status.DOWN;

		return new ApplicationHealth(currentHealthStatus.toString());
	}
}
