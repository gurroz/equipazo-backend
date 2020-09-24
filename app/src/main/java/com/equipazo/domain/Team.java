package com.equipazo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Setter
@Getter
@AllArgsConstructor
public class Team {
    private Long id;
    private String name;
    private String emblem;

    public String getFolderPath(String baseDir) {
        return baseDir + File.separator
                + this.name.replaceAll("\\W+", "") + File.separator;
    }
}
