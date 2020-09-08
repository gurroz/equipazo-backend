package com.equipazo.app.service;

import com.equipazo.app.port.in.SaveTeamUseCase;
import com.equipazo.app.port.out.SaveTeamPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTeamService implements SaveTeamUseCase {

    private final SaveTeamPort saveTeamPort;

    @Override
    public void saveTeam(TeamData team) {
        saveTeamPort.saveTeam(team.mapToTeam());
    }
}
