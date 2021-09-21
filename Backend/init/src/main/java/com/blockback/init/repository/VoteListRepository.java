package com.blockback.init.repository;

import com.blockback.init.entity.VoteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteListRepository extends JpaRepository<VoteList, Long> {
}
