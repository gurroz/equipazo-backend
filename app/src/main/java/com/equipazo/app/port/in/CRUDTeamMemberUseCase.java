package com.equipazo.app.port.in;

import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import org.springframework.core.io.InputStreamSource;

import java.io.InputStream;

public interface CRUDTeamMemberUseCase {
    TeamMember create(Long id, Team team, String name, String mobile, InputStreamSource picture);
    TeamMember getTeamMember(Long id, Team team);
    InputStream getTeamMemberProfilePic(long id, long teamId);

}
