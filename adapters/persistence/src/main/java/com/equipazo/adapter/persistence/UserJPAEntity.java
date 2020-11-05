package com.equipazo.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserJPAEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String mobile;
}
