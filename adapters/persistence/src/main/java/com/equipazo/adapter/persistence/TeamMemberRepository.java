package com.equipazo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface TeamMemberRepository extends JpaRepository<TeamMemberJPAEntity, TeamMemberJPAId> {
}
