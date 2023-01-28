package com.equipazo.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Match {
    private long id;
    private Formation homeTeam;
    private Formation awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Date startDate;
    private Date finishDate;
    private Location location;
    private List<MatchRecord> records;
}
