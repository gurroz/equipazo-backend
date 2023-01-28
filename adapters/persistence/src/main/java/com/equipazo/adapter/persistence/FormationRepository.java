package com.equipazo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface FormationRepository extends JpaRepository<TeamJPAEntity, Long> {
}
