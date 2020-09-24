package com.equipazo.adapter.rest;

import com.equipazo.domain.Team;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeamDataDTO {

    private Long id;
    @NotNull
    private String name;
    private String emblemURL;


    public TeamDataDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }

    public TeamDataDTO(Team team, String baseURL) {
        this(team);
        this.emblemURL = calculateEmblemURL(baseURL);
    }

    private String calculateEmblemURL(String baseURL) {
        return baseURL + this.id + "/emblem";
    }

    public Team mapToTeam() {
        return new Team(this.getId(), this.getName(), null);
    }
}
