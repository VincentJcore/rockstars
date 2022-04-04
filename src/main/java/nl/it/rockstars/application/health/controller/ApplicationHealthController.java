package nl.it.rockstars.application.health.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.it.rockstars.application.health.controller.dto.outbound.ApplicationHealthDTO;
import nl.it.rockstars.application.health.service.ApplicationHealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/application/health")
@RequiredArgsConstructor
public class ApplicationHealthController {

	private final ApplicationHealthService applicationHealthService;

	@GetMapping
	public ApplicationHealthDTO applicationHealthInfo() {

		final var currentHealth = applicationHealthService.getCurrentHealth();

		log.info("Application health requested: {}", currentHealth);

		return new ApplicationHealthDTO(currentHealth);
	}

}
