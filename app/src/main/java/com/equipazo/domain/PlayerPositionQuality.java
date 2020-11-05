package com.equipazo.domain;

import lombok.Data;

import java.util.Set;

@Data
public class PlayerPositionQuality {
    private TeamMember player;
    private PlayerPosition position;
    private Set<PlayerQuality> qualities;
}