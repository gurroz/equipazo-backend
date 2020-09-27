package com.equipazo.adapter.rest;

import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.equipazo.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/rest/teams/")
@RequiredArgsConstructor
public class CRUDTeamController {

    private final CRUDTeamUseCase CRUDTeamUseCase;

    @CrossOrigin
    @PostMapping
    public void saveTeam(@RequestParam(name = "teamData") @RequestBody TeamDataDTO teamDTO
            , @RequestParam(name = "file", required = false) @Valid final MultipartFile emblemFile
            , HttpServletResponse response) {

        try {
            log.debug("POST saveTeam: {}, {}", teamDTO, emblemFile);

            response.setStatus(HttpStatus.OK.value());
            Team team = teamDTO.mapToTeam();

            CRUDTeamUseCase.saveTeam(team, emblemFile);
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping
    public List<TeamDataDTO> getTeams(HttpServletResponse response) {
        List<TeamDataDTO> responseBody = new ArrayList<>();
        log.debug("Starting getTeams");
        try {
            response.setStatus(HttpStatus.OK.value());
            List<Team> teams = CRUDTeamUseCase.getTeams();
            responseBody = teams.stream().map(team -> new TeamDataDTO(team, "/rest/teams/")).collect(Collectors.toList());
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseBody;
    }

    @GetMapping("/{id}")
    public TeamDataDTO getTeam(@PathVariable long id, HttpServletResponse response) {
        TeamDataDTO responseBody = null;
        log.debug("Starting getTeams");
        try {
            response.setStatus(HttpStatus.OK.value());
            Team team = CRUDTeamUseCase.getTeam(id);
            responseBody = new TeamDataDTO(team, "/rest/teams/");
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseBody;
    }

    @GetMapping(value = "/{id}/emblem",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getEmblem(@PathVariable long id, HttpServletResponse response) {
        try {
            InputStream in = CRUDTeamUseCase.getTeamEmblem(id);
            return IOUtils.toByteArray(in);
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return null;
    }
}