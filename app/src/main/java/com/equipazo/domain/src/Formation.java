package com.equipazo.domain.src;

import lombok.Data;

import java.util.Map;

@Data
public class Formation {
    private long id;
    private Squad squad;
    private Map<PlayerPosition, Player> positions;
}
