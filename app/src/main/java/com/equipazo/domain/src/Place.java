package com.equipazo.domain.src;

import lombok.Data;

import java.util.Map;

@Data
public class Place {
    private long id;
    private String name;
    private String address;
    private String court;
    private long lat;
    private long lng;
}
