package com.equipazo.app.port.out;

import com.equipazo.domain.Team;

import java.util.List;

public interface CRUDTeamPort {
    void saveTeam(Team team);
    List<Team> getTeams();
    Team getTeam(long id);
}
