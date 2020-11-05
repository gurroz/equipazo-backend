package com.equipazo.app.port.out;

import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import com.equipazo.domain.User;

import java.util.Optional;
import java.util.Set;

public interface CRUDTeamMemberPort {
    TeamMember save(TeamMember member, Team team);
    Optional<TeamMember> get(long userId, Team team);
    Optional<User> getUser(long userId);
    Set<TeamMember> getByTeam(Team team);
}
