package com.equipazo.adapter.persistence;

import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class TeamPersistenceAdapter implements CRUDTeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Team saveTeam(Team team) {
        TeamJPAEntity entity = teamMapper.mapToJpaEntity(team);
        log.debug("Saving TeamJpa: {}", entity);
        entity = teamRepository.save(entity);

        return teamMapper.mapToTeam(entity);
    }

    @Override
    public List<Team> getTeams() {
        List<TeamJPAEntity> entities = teamRepository.findAll();
        return entities.stream().map(entity -> teamMapper.mapToTeam(entity)).collect(Collectors.toList());
    }

    @Override
    public Optional<Team> getTeam(long id) {
        try {
            TeamJPAEntity entity = teamRepository.getOne(id);
            return Optional.of(teamMapper.mapToTeam(entity));
        } catch(EntityNotFoundException e) {
            log.debug("Entity not found getTeam: {}", e);
        } catch(Exception e) {
            log.error("Error getTeam: {}", e);
        }

        return Optional.empty();
    }
}