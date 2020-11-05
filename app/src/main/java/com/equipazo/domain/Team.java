package com.equipazo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class Team {
    private Long id;
    private String name;
    private String emblem;
    private Set<TeamMember> players;
    private Set<TeamMember> coaches;

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
        this.emblem = "";
        this.players = new HashSet<>();
        this.coaches = new HashSet<>();

    }

    public Team(Long id, String name, String emblem) {
        this.id = id;
        this.name = name;
        this.emblem = emblem;
        this.players = new HashSet<>();
        this.coaches = new HashSet<>();

    }

    public String getFolderPath(String baseDir) {
        return baseDir + File.separator
                + this.name.replaceAll("\\W+", "") + File.separator;
    }
}
