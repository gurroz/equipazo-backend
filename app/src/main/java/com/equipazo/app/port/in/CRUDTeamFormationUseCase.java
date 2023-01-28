package com.equipazo.app.port.in;

import com.equipazo.domain.Formation;

public interface CRUDTeamFormationUseCase {

    Formation getActiveFormation(long teamId);
}
