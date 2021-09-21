package com.blockback.init.service;

import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardsByClubId(Long clubid);

    Board getBoardByBoardId(Long clubid, Long boardid);

    Board createBoard(BoardRegisterReq boardinfo, Long clubid);
}
