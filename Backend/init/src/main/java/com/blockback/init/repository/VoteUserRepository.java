package com.blockback.init.repository;

import com.blockback.init.entity.VoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface VoteUserRepository extends JpaRepository<VoteUser, Long> {
    Optional<VoteUser> findByVoteIdAndUserId(Long id, Long id1);

    @Transactional
    void deleteAllByVoteId(Long voteid);
}
