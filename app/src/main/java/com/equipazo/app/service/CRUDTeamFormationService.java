package com.equipazo.app.service;

import com.equipazo.app.port.in.CRUDTeamFormationUseCase;
import com.equipazo.app.port.in.CRUDTeamMemberUseCase;
import com.equipazo.app.port.in.CRUDTeamUseCase;
import com.equipazo.app.port.out.CRUDTeamMemberPort;
import com.equipazo.app.port.out.CRUDTeamPort;
import com.equipazo.app.port.out.SaveFilePort;
import com.equipazo.domain.Formation;
import com.equipazo.domain.Team;
import com.equipazo.domain.TeamMember;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CRUDTeamFormationService implements CRUDTeamFormationUseCase {

    private final CRUDTeamMemberUseCase CRUDTeamMemberUseCase;

    private final CRUDTeamPort CRUDTeamPort;
    private final CRUDTeamMemberPort CRUDTeamMemberPort;
    private final SaveFilePort saveFilePort;
    private final SaveTeamProperties saveTeamProperties;

    @Override
    public Formation getActiveFormation(long teamId) {
        return null;
    }
}
