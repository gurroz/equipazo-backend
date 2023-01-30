package com.equipazo.adapter.rest;

import com.equipazo.app.port.in.CRUDTeamUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CRUDTeamController.class)
public class CRUDTeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private CRUDTeamUseCase CRUDTeamUseCase;

//    @Test
//    void testCreateTeam() throws Exception {
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
//    }

//    @Test
//    void testSaveTeam() throws Exception {
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
//    }

    private static TeamDataDTO getTeam() {
        return new TeamDataDTO("Villa Roman");
    }

    private static TeamDataDTO getSavedTeam() {
        return new TeamDataDTO("Villa Roman");
    }
}
