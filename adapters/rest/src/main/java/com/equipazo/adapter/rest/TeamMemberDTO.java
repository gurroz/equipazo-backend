package com.equipazo.adapter.rest;

import com.equipazo.domain.TeamMember;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeamMemberDTO {
    public static final String COACH = "COACH";
    public static final String PLAYER = "PLAYER";

    private Long id;
    @NotNull
    private String name;
    private String mobile;
    private String picture;
    private String type;

    public TeamMemberDTO(TeamMember teamMember) {
        this.id = teamMember.getId();
        this.name = teamMember.getUser().getName();
        this.mobile = teamMember.getUser().getMobile();
        this.picture = teamMember.getImage();
        this.type = "";
    }

    public TeamMemberDTO(TeamMember teamMember, String baseURL, String type) {
        this.id = teamMember.getId();
        this.name = teamMember.getUser().getName();
        this.mobile = teamMember.getUser().getMobile();
        this.picture = baseURL + "/member/" + teamMember.getId() + "/profilePic";
        this.type = type;
    }

    public boolean isCoach() {
        return COACH.equals(type);
    }


}
