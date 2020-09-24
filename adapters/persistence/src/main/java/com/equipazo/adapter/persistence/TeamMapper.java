package com.equipazo.adapter.persistence;

import com.equipazo.domain.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    TeamJPAEntity mapToJpaEntity(Team team) {
        return new TeamJPAEntity(
                team.getId(),
                team.getName(),
                team.getEmblem());
    }

    Team mapToTeam(TeamJPAEntity team) {
        return new Team(
                team.getId(),
                team.getName(),
                team.getEmblem());
    }
}
