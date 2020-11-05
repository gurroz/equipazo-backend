package com.equipazo.adapter.persistence;

import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import com.equipazo.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamMemberMapper {

    private final TeamMapper teamMapper;
    private final UserMapper userMapper;


    UserJPAEntity mapToJPAEntity(User user) {
        return new UserJPAEntity(user.getId()
                , user.getName()
                , user.getMobile());
    }

    TeamMemberJPAEntity mapToJpaEntity(TeamMember teamMember, Team team) {
        UserJPAEntity userJPA = mapToJPAEntity(teamMember.getUser());
        TeamJPAEntity teamJPA = teamMapper.mapToJpaEntity(team);
        TeamMemberJPAId id = new TeamMemberJPAId(userJPA, teamJPA);

        return new TeamMemberJPAEntity(id, teamMember.getImage());
    }

    TeamMember mapToDomain(TeamMemberJPAEntity teamMemberJPA) {
        TeamMemberJPAId ids = teamMemberJPA.getId();
        UserJPAEntity userJPA = ids.getUser();

        return new TeamMember(
                userJPA.getId()
                , userMapper.mapToUser(userJPA)
                , teamMemberJPA.getImage());
    }
}
