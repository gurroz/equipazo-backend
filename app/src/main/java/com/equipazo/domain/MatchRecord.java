package com.equipazo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MatchRecord {
    private long id;
    private RecordType type;
    private Date date;
    private Formation team;
    private Player player;
}
