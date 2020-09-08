package com.equipazo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TeamRepository extends JpaRepository<TeamJPAEntity, Long> {
}
