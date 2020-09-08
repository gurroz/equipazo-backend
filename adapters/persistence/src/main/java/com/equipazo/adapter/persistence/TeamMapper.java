package com.equipazo.adapter.persistence;

import com.equipazo.domain.src.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    TeamJPAEntity mapToJpaEntity(Team team) {
        return new TeamJPAEntity(
                team.getId(),
                team.getName(),
                team.getEmblem());
    }
}
