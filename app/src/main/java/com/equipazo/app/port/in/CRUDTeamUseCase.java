package com.equipazo.app.port.in;

import com.equipazo.domain.Team;
import org.springframework.core.io.InputStreamSource;

import java.io.InputStream;
import java.util.List;

public interface CRUDTeamUseCase {
    void saveTeam(Team team, InputStreamSource emblem);
    List<Team> getTeams();
    Team getTeam(long id);
    InputStream getTeamEmblem(long id);
}
