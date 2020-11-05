package com.equipazo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String mobile;
}
