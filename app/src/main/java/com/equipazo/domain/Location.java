package com.equipazo.domain;

import lombok.Data;

@Data
public class Location {
    private long id;
    private String name;
    private String address;
    private String court;
    private long lat;
    private long lng;
}
