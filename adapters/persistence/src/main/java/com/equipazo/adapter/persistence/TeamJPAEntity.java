package com.equipazo.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class TeamJPAEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @Column
    private String emblem;
}
