package com.equipazo.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@Table(name = "formation")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormationJPAEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TeamJPAEntity team; //TODO: Finish JPA entities
}
