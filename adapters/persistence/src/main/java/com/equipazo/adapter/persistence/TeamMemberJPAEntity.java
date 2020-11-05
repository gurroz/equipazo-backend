package com.equipazo.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "team_member")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamMemberJPAEntity {

    @EmbeddedId
    private TeamMemberJPAId id;

    @Column
    private String image;
}
