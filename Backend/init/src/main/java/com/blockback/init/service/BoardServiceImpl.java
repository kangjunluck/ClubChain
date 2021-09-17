package com.blockback.init.service;


import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.entity.Board;
import com.blockback.init.repository.BoardRepository;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public List<Board> getBoardsByClubId(Long clubid) {
        List<Board> boardList = boardRepository.findBoardsByClubId(clubid);
        return boardList;
    }

    @Override
    public Board getBoardByBoardId(Long clubid, Long boardid) {
        return boardRepository.findBoardByBoardId(clubid, boardid);
    }

    @Override
    public Board createBoard(BoardRegisterReq boardinfo, Long clubid) {
        return null;
    }
}
