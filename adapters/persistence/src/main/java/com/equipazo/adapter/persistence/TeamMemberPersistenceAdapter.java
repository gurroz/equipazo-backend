package com.equipazo.adapter.persistence;

import com.equipazo.app.port.out.CRUDTeamMemberPort;
import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import com.equipazo.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class TeamMemberPersistenceAdapter implements CRUDTeamMemberPort {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;
    private final TeamMapper teamMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public TeamMember save(TeamMember member, Team team) {
        UserJPAEntity userJPA = userMapper.mapToJpaEntity(member.getUser());
        userJPA = userRepository.save(userJPA);

        TeamMemberJPAEntity entity = teamMemberMapper.mapToJpaEntity(member, team);
        TeamJPAEntity teamJPA = teamMapper.mapToJpaEntity(team);

        TeamMemberJPAId combinedId = new TeamMemberJPAId(userJPA, teamJPA);
        entity.setId(combinedId);

        log.debug("Saving TeamMemberJpa: {}", entity);
        entity = teamMemberRepository.save(entity);

        return teamMemberMapper.mapToDomain(entity);
    }


    @Override
    public Optional<TeamMember> get(long userId, Team team) {
        try {
            UserJPAEntity user = userRepository.getOne(userId);
            TeamJPAEntity teamJPA = teamMapper.mapToJpaEntity(team);

            TeamMemberJPAId combinedId = new TeamMemberJPAId(user, teamJPA);
            TeamMemberJPAEntity entity = teamMemberRepository.getOne(combinedId);
            return Optional.of(teamMemberMapper.mapToDomain(entity));
        } catch(EntityNotFoundException e) {
            log.debug("Entity not found get: {}", e);
        } catch(Exception e) {
            log.error("Error get: {}", e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(long userId) {
        try {
            UserJPAEntity entity = userRepository.getOne(userId);
            return Optional.of(userMapper.mapToUser(entity));
        } catch(EntityNotFoundException e) {
            log.debug("Entity not found getUser: {}", e);
        } catch(Exception e) {
            log.error("Error getUser: {}", e);
        }

        return Optional.empty();
    }

    @Override
    public Set<TeamMember> getByTeam(Team team) {
        TeamJPAEntity teamJPA = teamMapper.mapToJpaEntity(team);
        TeamMemberJPAId combinedId = new TeamMemberJPAId(null, teamJPA);
        TeamMemberJPAEntity memberJPAEntity = new TeamMemberJPAEntity(combinedId, null);
        Example<TeamMemberJPAEntity>  example = Example.of(memberJPAEntity);
        return teamMemberRepository.findAll(example)
                .stream()
                .map(memberJPA -> teamMemberMapper.mapToDomain(memberJPA))
                .collect(Collectors.toSet());
    }
}