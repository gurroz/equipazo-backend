package com.equipazo.adapter.persistence;

import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class TeamPersistenceAdapter implements CRUDTeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public void saveTeam(Team team) {
        TeamJPAEntity entity = teamMapper.mapToJpaEntity(team);
        log.debug("Saving TeamJpa: {}", entity);
        teamRepository.save(entity);
    }

    @Override
    public List<Team> getTeams() {
        List<TeamJPAEntity> entities = teamRepository.findAll();
        return entities.stream().map(entity -> teamMapper.mapToTeam(entity)).collect(Collectors.toList());
    }

    @Override
    public Team getTeam(long id) {
        TeamJPAEntity entity = teamRepository.getOne(id);
        return teamMapper.mapToTeam(entity);
    }
}