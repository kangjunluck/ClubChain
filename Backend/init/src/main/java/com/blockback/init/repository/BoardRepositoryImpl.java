package com.blockback.init.repository;

import com.blockback.init.entity.Board;
import com.blockback.init.entity.QBoard;
import com.blockback.init.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QBoard qBoard = QBoard.board;

    public List<Board> findBoardsByClubId(Long clubid) {
//        List<Board> boards = jpaQueryFactory.select(qBoard).from(qBoard)
//                .where(qBoard.clubid.eq(clubid)).fetchAll();
//        return boards;
        return null;
    }

    public Optional<Board> findBoardByBoardId(Long clubid, Long boardid) {
//        Board board = jpaQueryFactory.select(qBoard).from(qBoard)
//                .where(qBoard.clubid.eq(clubid), qBoard.id.eq(boardid));
//        if(board == null) return Optional.ofNullable(new Board());
//        return Optional.ofNullable(board);
        return null;

    }
}
