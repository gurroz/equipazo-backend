package main.java.com.equipazo.adapter.lambda;

import com.equipazo.app.port.in.CRUDTeamMemberUseCase;
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
    private final CRUDTeamMemberUseCase CRUDTeamMemberUseCase;

    @CrossOrigin
    @PostMapping
    public void createTeam(@RequestParam(name = "data") @RequestBody TeamDataDTO teamDTO
            , @RequestParam(name = "file", required = false) @Valid final MultipartFile emblemFile
            , HttpServletResponse response) {

        try {
            log.debug("POST createTeam: {}, {}", teamDTO, emblemFile);

            response.setStatus(HttpStatus.CREATED.value());
            Team team = teamDTO.mapToTeam();

            CRUDTeamUseCase.createTeam(team, emblemFile);
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void saveTeam(@PathVariable long id
            , @RequestParam(name = "data") @RequestBody TeamDataDTO teamDTO
            , @RequestParam(name = "file", required = false) @Valid final MultipartFile emblemFile
            , HttpServletResponse response) {

        try {
            log.debug("POST saveTeam: {}, {}, {}", id, teamDTO, emblemFile);
            response.setStatus(HttpStatus.OK.value());

            CRUDTeamUseCase.saveTeam(id, teamDTO.getName(), emblemFile);
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @CrossOrigin
    @PutMapping("/{id}/team-member")
    public void saveTeamMember(@PathVariable long id
            , @RequestParam(name = "data") @RequestBody TeamMemberDTO teamMember
            , @RequestParam(name = "file", required = false) @Valid final MultipartFile pictureFile
            , HttpServletResponse response) {
        try {
            log.debug("POST saveTeamCoaches: {}, {}, {}", id, teamMember, pictureFile);

            response.setStatus(HttpStatus.OK.value());
            if(teamMember.isCoach()) {
                CRUDTeamUseCase.saveTeamCoach(id, teamMember.getId(), teamMember.getName(), teamMember.getMobile(), pictureFile);
            } else {
                CRUDTeamUseCase.saveTeamPlayer(id, teamMember.getId(), teamMember.getName(), teamMember.getMobile(), pictureFile);
            }
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

        log.debug("Response is: {}", responseBody);

        return responseBody;
    }

    @GetMapping("/{id}")
    public TeamDataDTO getTeam(@PathVariable long id, HttpServletResponse response) {
        TeamDataDTO responseBody = null;
        log.debug("Starting getTeams");
        try {
            response.setStatus(HttpStatus.OK.value());
            Team team = CRUDTeamUseCase.getTeam(id);
            log.debug("GET TEAMS: {}",team);
            responseBody = new TeamDataDTO(team, "/rest/teams/");
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        log.debug("Response is: {}", responseBody);
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

    @GetMapping(value = "/{teamId}/member/{memberId}/profilePic",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getTeamMemberProfilePic(@PathVariable long teamId
            , @PathVariable long memberId, HttpServletResponse response) {
        try {
            InputStream in = CRUDTeamMemberUseCase.getTeamMemberProfilePic(memberId, teamId);
            return IOUtils.toByteArray(in);
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return null;
    }

    @GetMapping("/{id}/formations")
    public TeamDataDTO getTeamFormations(@PathVariable long id, HttpServletResponse response) {
        TeamDataDTO responseBody = null;
        log.debug("Starting getTeams");
        try {
            response.setStatus(HttpStatus.OK.value());
            Team team = CRUDTeamUseCase.getTeam(id);
            log.debug("GET TEAMS: {}",team);
            responseBody = new TeamDataDTO(team, "/rest/teams/");
        } catch(Exception e) {
            log.error("Error is: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        log.debug("Response is: {}", responseBody);
        return responseBody;
    }
}