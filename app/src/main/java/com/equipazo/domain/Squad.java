package com.equipazo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Squad {
    private long id;
    private int numberPlayers;
    private List<Player> players;
}
