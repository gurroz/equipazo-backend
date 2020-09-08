package com.equipazo.adapters.rest;

import com.equipazo.app.port.in.SaveTeamUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SaveTeamController.class)
public class SaveTeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaveTeamUseCase saveTeamUseCase;

    @Test
    void testSaveTeam() throws Exception {

        SaveTeamUseCase.TeamData testTeam = getTeam();

        mockMvc.perform(post("/rest/teams/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testTeam)))
                .andExpect(status().isOk());

        then(saveTeamUseCase).should()
                .saveTeam(eq(testTeam));
    }

    public static String asJsonString(final Object testTeam) {
        try {
            return new ObjectMapper().writeValueAsString(testTeam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SaveTeamUseCase.TeamData getTeam() {
        return new SaveTeamUseCase.TeamData(
                "Villa Roman",
                "http://villaRoma.jpg");
    }
}
