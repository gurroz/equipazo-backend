package com.equipazo.domain;

import lombok.Data;

@Data
public class Player {
    private long id;
    private User user;
    private String image;
}
