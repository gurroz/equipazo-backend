package com.equipazo.domain.src;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class MatchRecord {
    private long id;
    private RecordType type;
    private Date date;
    private Formation team;
    private Player player;
}
