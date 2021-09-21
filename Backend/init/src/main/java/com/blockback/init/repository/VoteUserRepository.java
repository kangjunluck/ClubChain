package com.blockback.init.repository;

import com.blockback.init.entity.VoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteUserRepository extends JpaRepository<VoteUser, Long> {
}
