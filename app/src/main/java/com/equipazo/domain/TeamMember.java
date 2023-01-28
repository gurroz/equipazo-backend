package com.equipazo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;


@Setter
@Getter
@AllArgsConstructor
public class TeamMember {
    private Long id;
    private User user;
    private String image;
    private Integer jersey;

    public String getFolderPath(String baseDir) {
        return baseDir + File.separator
                + this.user.getName().replaceAll("\\W+", "") + File.separator;
    }
}
