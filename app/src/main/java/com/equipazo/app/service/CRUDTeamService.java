package com.equipazo.app.service;

import com.equipazo.app.port.in.CRUDTeamMemberUseCase;
import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.equipazo.app.port.out.CRUDTeamMemberPort;
import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.app.port.out.SaveFilePort;
import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CRUDTeamService implements CRUDTeamUseCase {

    private final CRUDTeamMemberUseCase CRUDTeamMemberUseCase;

    private final CRUDTeamPort CRUDTeamPort;
    private final CRUDTeamMemberPort CRUDTeamMemberPort;
    private final SaveFilePort saveFilePort;
    private final SaveTeamProperties saveTeamProperties;

    @Override
    public void createTeam(Team team, InputStreamSource emblemFile) {
        String fullEmblemPath = "";
        try {
            if(emblemFile != null) {
                fullEmblemPath = saveFilePort.saveFile(emblemFile, team.getFolderPath(saveTeamProperties.getFilesURI()));
            }
        } catch (IOException e) {
            log.error("Error saveTeam: {}", e);
        }
        team.setEmblem(fullEmblemPath);

        CRUDTeamPort.saveTeam(team);
    }

    @Override
    public void saveTeam(long teamId, String name, InputStreamSource emblemFile) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(teamId);
        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();
            if(name != null) {
                team.setName(name);
            }

            this.createTeam(team, emblemFile);
        }
    }

    @Override
    public void saveTeamPlayer(long teamId, Long playerId, String name, String mobile, InputStreamSource picture) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(teamId);
        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();

            Set<TeamMember> players = team.getPlayers();
            if (players == null) {
                players = new HashSet<>();
            }

            TeamMember newMember = CRUDTeamMemberUseCase.getTeamMember(playerId, team);
            if (newMember == null) {
                newMember = CRUDTeamMemberUseCase.create(playerId, team, name, mobile, picture);
            }

            players.add(newMember);
            team.setPlayers(players);

            CRUDTeamPort.saveTeam(team);
        }
    }

    @Override
    public void saveTeamCoach(long teamId, Long coachId, String name, String mobile, InputStreamSource picture) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(teamId);
        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();
            Set<TeamMember> coaches = team.getCoaches();
            if (coaches == null) {
                coaches = new HashSet<>();
            }

            TeamMember newMember = CRUDTeamMemberUseCase.getTeamMember(coachId, team);
            if (newMember == null) {
                newMember = CRUDTeamMemberUseCase.create(null, team, name, mobile, picture);
            }

            coaches.add(newMember);
            team.setCoaches(coaches);

            CRUDTeamPort.saveTeam(team);
        }
    }


    @Override
    public List<Team> getTeams() {
        return CRUDTeamPort.getTeams();
    }

    @Override
    public Team getTeam(long id) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(id);
        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();
            Set<TeamMember> members = CRUDTeamMemberPort.getByTeam(team);
            team.setCoaches(members);

            return team;
        }

        return null;
    }

    @Override
    @SneakyThrows
    public InputStream getTeamEmblem(long id) {
        Optional<Team> teamOpt = CRUDTeamPort.getTeam(id);
        if(teamOpt.isPresent()) {
            Team team = teamOpt.get();
            String emblemPath = team.getEmblem();

            log.debug("Emblem path is: {}", emblemPath);
            File initialFile = new File(emblemPath);
            return new FileInputStream(initialFile);
        }

        return null;
    }
}
