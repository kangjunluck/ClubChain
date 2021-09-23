package com.blockback.init.repository;


import com.blockback.init.entity.Board;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByClub(Club club);

    Board findByIdAndClub(Long boardid, Club club);
}
