package com.equipazo.domain;

import lombok.Data;

import java.util.Set;

@Data
public class PlayerPositionQuality {
    private Player player;
    private PlayerPosition position;
    private Set<PlayerQuality> qualities;
}