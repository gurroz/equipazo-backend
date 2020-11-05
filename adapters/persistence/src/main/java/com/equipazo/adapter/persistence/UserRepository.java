package com.equipazo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserJPAEntity, Long> {
}
