package com.equipazo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Formation {
    private long id;
    private Team team;
    private Map<PlayerPosition, TeamMember> players;
    private Map<PlayerPosition, TeamMember> bench;

}
