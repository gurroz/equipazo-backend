package com.equipazo.domain.src;

import lombok.Value;

@Value
public class Team {
    private final Long id;
    private final String name;
    private final String emblem;
}
