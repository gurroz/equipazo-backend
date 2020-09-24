package com.equipazo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface TeamRepository extends JpaRepository<TeamJPAEntity, Long> {
}
