package com.blockback.init.repository;


import com.blockback.init.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardsByClubId(Long clubid);

    Board findBoardByBoardId(Long clubid, Long boardid);
}
