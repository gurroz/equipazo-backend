package com.equipazo.app.port.out;

import com.equipazo.domain.Formation;

import java.util.Optional;

public interface CRUDTeamFormationPort {
    Optional<Formation> getTeamFormation(long teamId);
}
