package com.equipazo.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamMemberJPAId implements Serializable {

    @NotNull
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private UserJPAEntity user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamJPAEntity team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMemberJPAId that = (TeamMemberJPAId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, team);
    }
}
