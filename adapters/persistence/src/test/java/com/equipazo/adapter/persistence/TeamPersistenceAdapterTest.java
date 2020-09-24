package com.equipazo.adapter.persistence;

import com.equipazo.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TeamPersistenceAdapter.class, TeamMapper.class})
public class TeamPersistenceAdapterTest {

    @Autowired
    private TeamPersistenceAdapter adapterUnderTest;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void updatesActivities() {
        Team team = new Team(null, "Naranja", "http://www.google.com/naranja");

        adapterUnderTest.saveTeam(team);

        assertThat(teamRepository.count()).isEqualTo(1);

        Team savedTeam = adapterUnderTest.getTeams().get(0);
        assertThat(savedTeam.getName()).isEqualTo("Naranja");
    }
}
