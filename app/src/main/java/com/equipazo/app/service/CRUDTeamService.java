package com.equipazo.app.service;

import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.equipazo.app.port.out.SaveFilePort;
import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CRUDTeamService implements CRUDTeamUseCase {

    private final CRUDTeamPort CRUDTeamPort;
    private final SaveFilePort saveFilePort;
    private final SaveTeamProperties saveTeamProperties;

    @Override
    public void saveTeam(Team team, InputStreamSource emblemFile) {
        String fullEmblemPath = "";
        try {
            if(emblemFile != null) {
                fullEmblemPath = saveFilePort.saveFile(emblemFile, team.getFolderPath(saveTeamProperties.getFilesURI()));
            }
        } catch (IOException e) {
            log.error("Error saveTeam: {}", e);
        }

        team.setEmblem(fullEmblemPath);

        CRUDTeamPort.saveTeam(team);
    }

    @Override
    public List<Team> getTeams() {
        return CRUDTeamPort.getTeams();
    }

    @Override
    public Team getTeam(long id) {
        return CRUDTeamPort.getTeam(id);
    }

    @Override
    @SneakyThrows
    public InputStream getTeamEmblem(long id) {
        Team team = CRUDTeamPort.getTeam(id);
        String emblemPath = team.getEmblem();

        File initialFile = new File(emblemPath);
        return new FileInputStream(initialFile);
    }
}
