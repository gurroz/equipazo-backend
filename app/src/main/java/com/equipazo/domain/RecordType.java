package com.equipazo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class RecordType {
    private long id;
    private String name;
    private boolean isScore;
}
