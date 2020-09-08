package com.equipazo.domain.src;

import lombok.Data;

import java.util.List;

@Data
public class Squad {
    private long id;
    private int numberPlayers;
    private List<Player> players;
}
