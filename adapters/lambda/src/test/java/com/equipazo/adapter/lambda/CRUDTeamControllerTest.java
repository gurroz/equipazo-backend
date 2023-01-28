package test.java.com.equipazo.adapter.lambda;

import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CRUDTeamController.class)
public class CRUDTeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CRUDTeamUseCase CRUDTeamUseCase;

    @Test
    void testCreateTeam() throws Exception {
//        TeamDataDTO testTeam = getTeam();
//        HashMap<String, String> contentTypeParams = new HashMap<String, String>();
//        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);
//
//        MockMultipart multipartFile = new MockMultipartFile("data", testTeam);
//
//        mockMvc.perform(post("/rest/teams/")
//                .contentType(mediaType)
//                .content(multipartFile.getBytes()))
//                .andExpect(status().isOk());
//
//        then(CRUDTeamUseCase).should()
//                .createTeam(eq(testTeam.mapToTeam()), null);
    }

    @Test
    void testSaveTeam() throws Exception {
//        TeamDataDTO testTeam = getSavedTeam();
//        DataTeam data = new DataTeam(testTeam);
//
//        mockMvc.perform(put("/rest/teams/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(data)))
//                .andExpect(status().isOk());
//
//        then(CRUDTeamUseCase).should()
//                .saveTeam(testTeam.getId(), testTeam.getName(),null);
    }

    private static TeamDataDTO getTeam() {
        return new TeamDataDTO("Villa Roman");
    }

    private static TeamDataDTO getSavedTeam() {
        return new TeamDataDTO("Villa Roman");
    }
}
