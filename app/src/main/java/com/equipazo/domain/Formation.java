package com.equipazo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Formation {
    private long id;
    private Squad squad;
    private Map<PlayerPosition, TeamMember> positions;
}
