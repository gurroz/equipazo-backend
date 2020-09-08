package com.equipazo.app.port.in;

import com.equipazo.domain.src.Team;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public interface SaveTeamUseCase {
    void saveTeam(TeamData team);

    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    class TeamData {
        @NotNull
        private String name;
        private String emblem;

        private TeamData() {
            this.name = "";
            this.emblem = "";
        }
        public Team mapToTeam() {
            return new Team(null, this.name, this.emblem);
        }
    }
}
