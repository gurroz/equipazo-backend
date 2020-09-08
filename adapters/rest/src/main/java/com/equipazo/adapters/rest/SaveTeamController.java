package com.equipazo.adapters.rest;

import com.equipazo.app.port.in.SaveTeamUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Component
@RestController
@RequiredArgsConstructor
public class SaveTeamController {

    @Autowired
    private final SaveTeamUseCase saveTeamUseCase;

    @PostMapping("/rest/teams/")
    void saveTeam(@RequestBody SaveTeamUseCase.TeamData teamData, HttpServletResponse response) {
        try {
            response.setStatus(HttpStatus.OK.value());
            saveTeamUseCase.saveTeam(teamData);
        } catch(Exception e) {
            System.out.println("Error is" + e.getStackTrace());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}