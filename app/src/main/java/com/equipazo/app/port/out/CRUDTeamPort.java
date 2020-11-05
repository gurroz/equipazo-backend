package com.equipazo.app.port.out;

import com.equipazo.domain.Team;

import java.util.List;
import java.util.Optional;

public interface CRUDTeamPort {
    Team saveTeam(Team team);
    List<Team> getTeams();
    Optional<Team> getTeam(long id);
}
