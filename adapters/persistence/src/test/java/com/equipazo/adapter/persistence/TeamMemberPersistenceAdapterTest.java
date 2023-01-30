package com.equipazo.adapter.persistence;

import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
import com.equipazo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TeamMemberPersistenceAdapter.class, TeamMemberMapper.class, UserMapper.class,
        TeamMapper.class, TeamPersistenceAdapter.class})
public class TeamMemberPersistenceAdapterTest {

    @Autowired
    private TeamMemberPersistenceAdapter adapterUnderTest;

    @Autowired
    private TeamPersistenceAdapter teamAdapter;

    @Autowired
    private TeamMemberRepository repository;

    @Test
    void createTeamMember() {
        long id = 1l;
        User user = new User(null, "Juanito", "1234567");
        TeamMember teamMember = new TeamMember(null, user, "http://www.google.com/naranja", 0);

        Team team = new Team(null, "La naranja");
        team = teamAdapter.saveTeam(team);

        teamMember = adapterUnderTest.save(teamMember, team);

        assertThat(repository.count()).isEqualTo(1);

        Optional<TeamMember> savedTeamMember = adapterUnderTest.get(teamMember.getId(), team);
        assertThat(savedTeamMember.isPresent());
        assertThat(savedTeamMember.get().getUser().getName()).isEqualTo("Juanito");
    }
    
}
