package com.equipazo.app.port.in;

import com.equipazo.domain.Team;
import org.springframework.core.io.InputStreamSource;

import java.io.InputStream;
import java.util.List;

public interface CRUDTeamUseCase {
    void createTeam(Team team, InputStreamSource emblem);
    void saveTeam(long teamId, String name, InputStreamSource emblem);
    void saveTeamPlayer(long teamId, Long playerId, String name, String mobile, InputStreamSource picture);
    void saveTeamCoach(long teamId, Long coachId, String name, String mobile, InputStreamSource picture);
    List<Team> getTeams();
    Team getTeam(long id);
    InputStream getTeamEmblem(long id);
}
