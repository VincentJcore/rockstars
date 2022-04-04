package nl.it.rockstars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.it.rockstars.application.health.controller.ApplicationHealthController;
import nl.it.rockstars.application.health.controller.dto.outbound.ApplicationHealthDTO;
import nl.it.rockstars.application.health.service.ApplicationHealthService;
import nl.it.rockstars.application.health.service.model.ApplicationHealth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApplicationHealthController.class)
class ApplicationHealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApplicationHealthService applicationHealthService;

    @MockBean
    private AuthenticationProvider authenticationProvider;

    @Test
    @WithMockUser
    void applicationHealthInfo_providesHealthStatus() throws Exception {

        final var infoUrl = "/api/v1/application/health";

        final var applicationHealthStatus = new ApplicationHealth("LE_STATUS");
        when(applicationHealthService.getCurrentHealth()).thenReturn(applicationHealthStatus);

        final var result =
                this.mockMvc.perform(get(infoUrl)
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .accept(MediaType.APPLICATION_JSON)
                                             .header("Cache-Control", "no-cache"))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

        final var json = objectMapper.readTree(result.getResponse()
                                                     .getContentAsByteArray());

        assertThat(json.get("application_health")).isNotEmpty();

        final var applicationHealthDTO = objectMapper.readValue(result.getResponse()
                                                                      .getContentAsByteArray(), ApplicationHealthDTO.class);

        assertThat(applicationHealthDTO).isNotNull();
        assertThat(applicationHealthDTO.getApplicationHealth()).isNotNull();
        assertThat(applicationHealthDTO.getApplicationHealth().getStatus()).isEqualTo("LE_STATUS");
    }

}