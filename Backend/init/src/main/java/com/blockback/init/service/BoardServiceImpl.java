package com.blockback.init.service;


import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.Comment;
import com.blockback.init.entity.User;
import com.blockback.init.repository.BoardRepository;
import com.blockback.init.repository.ClubRepository;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ClubRepository clubRepository;

    @Override
    public List<Board> getBoardsByClubId(Long clubid) {
        Club club = clubRepository.findById(clubid).get();
        List<Board> boardList = boardRepository.findByClub(club);
        return boardList;
    }

    @Override
    public Board getBoardByBoardId(Long boardid) {
        Optional<Board> board = boardRepository.findById(boardid);
        if (board.isPresent()) {
            return board.get();
        }
        return null;
    }

    @Override
    public void createBoard(User user, BoardRegisterReq boardinfo, Long clubid) {
        Club club = clubRepository.findById(clubid).get();
        Board board = new Board();
        board.setUser(user);
        board.setClub(club);
        board.setTitle(boardinfo.getTitle());
        board.setContent(boardinfo.getContent());
        board.setCreated(new Date());
        board.setUpdated(new Date());
        board.setView(0);
        board.setSection(boardinfo.getSection());

        boardRepository.save(board);
    }

    @Override
    public void putBoard(BoardRegisterReq boardinfo, Long boardid) {
        Board board = boardRepository.findById(boardid).get();
        board.setTitle(boardinfo.getTitle());
        board.setContent(boardinfo.getContent());
        board.setUpdated(new Date());
        board.setSection(boardinfo.getSection());

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardid) {
        Board board = boardRepository.findById(boardid).get();
        boardRepository.delete(board);
    }
}
