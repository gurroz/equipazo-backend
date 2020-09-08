package com.equipazo.adapter.persistence;

import com.equipazo.app.port.out.SaveTeamPort;
import com.equipazo.domain.src.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TeamPersistenceAdapter implements SaveTeamPort {

    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private final TeamMapper teamMapper;

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(teamMapper.mapToJpaEntity(team));
    }
}