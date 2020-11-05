package com.equipazo.app.service;

import com.equipazo.app.port.in.CRUDTeamMemberUseCase;
import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.equipazo.app.port.out.CRUDTeamMemberPort;
import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.app.port.out.SaveFilePort;
import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import com.equipazo.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CRUDTeamMemberService implements CRUDTeamMemberUseCase {

    private final CRUDTeamMemberPort CRUDTeamMemberPort;
    private final SaveFilePort saveFilePort;
    private final SaveTeamProperties saveTeamProperties;
    private final CRUDTeamPort CRUDTeamPort;

    @Override
    public TeamMember create(Long id, Team team, String name, String mobile, InputStreamSource picture) {
        User newUser = new User(null, name, mobile);
        if(id != null) {
            Optional<User> userOptional = CRUDTeamMemberPort.getUser(id);
            if(userOptional.isPresent()) {
                newUser = userOptional.get();
            }
        }

        TeamMember newMember  = new TeamMember(newUser.getId(), newUser, "");
        String fullMemberPath = "";
        try {
            if(picture != null) {
                fullMemberPath = saveFilePort.saveFile(picture, newMember.getFolderPath(saveTeamProperties.getFilesURI()));
            }
        } catch (IOException e) {
            log.error("Error saveTeamPlayer: {}", e);
        }

        log.debug("Member path: {}, {}",fullMemberPath,  newMember.getFolderPath(saveTeamProperties.getFilesURI()));
        newMember.setImage(fullMemberPath);
        CRUDTeamMemberPort.save(newMember, team);

        return newMember;
    }


    @Override
    public TeamMember getTeamMember(Long id, Team team) {
        if(id == null)
            return null;

        Optional<TeamMember>  teamMemberOptional = CRUDTeamMemberPort.get(id, team);
        if(teamMemberOptional.isPresent()) {
            return teamMemberOptional.get();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public InputStream getTeamMemberProfilePic(long id, long teamId) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(teamId);
        if(teamOpt.isPresent()) {
            TeamMember teamMember = getTeamMember(id, teamOpt.get());
            if (teamMember != null) {
                log.debug("Emblem path is: {}", teamMember.getImage());
                File initialFile = new File(teamMember.getImage());
                return new FileInputStream(initialFile);
            }
        }
        return null;
    }
}
