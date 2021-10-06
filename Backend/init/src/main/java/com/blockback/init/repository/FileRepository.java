package com.blockback.init.repository;

import com.blockback.init.entity.Board;
import com.blockback.init.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<BoardFile, Long> {
    List<BoardFile> findByBoard(Board board);
    Optional<BoardFile> findByBoardId(Long id);
}
