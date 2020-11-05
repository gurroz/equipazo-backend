package com.equipazo.adapter.rest;

import com.equipazo.domain.Team;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<TeamMemberDTO> coaches;
    private Set<TeamMemberDTO> players;

    public TeamDataDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }

    public TeamDataDTO(String name) {
        this.name = name;
    }

    public TeamDataDTO(Team team, String baseURL) {
        this(team);
        this.emblemURL = calculateEmblemURL(baseURL);
        this.players = team.getCoaches()
                .stream()
                .map( teamMember -> new TeamMemberDTO(teamMember, this.getBaseUrl(baseURL), TeamMemberDTO.PLAYER))
                .collect(Collectors.toSet());
        this.coaches = team.getPlayers()
                .stream()
                .map( teamMember -> new TeamMemberDTO(teamMember, this.getBaseUrl(baseURL), TeamMemberDTO.COACH))
                .collect(Collectors.toSet());
    }

    private String getBaseUrl(String baseURL) {
        return baseURL + this.id;
    }

    private String calculateEmblemURL(String baseURL) {
        return this.getBaseUrl(baseURL) + "/emblem";
    }

    public Team mapToTeam() {
        return new Team(this.getId(), this.getName(), this.emblemURL);
    }
}
