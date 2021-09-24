package com.blockback.init.service;

import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.User;

import java.util.List;

public interface BoardService {
    List<Board> getBoardsByClubId(Long clubid);

    Board getBoardByBoardId(Long boardid);

    void createBoard(User user, BoardRegisterReq boardinfo, Long clubid);
    
    void putBoard(BoardRegisterReq boardinfo, Long boardid);

    void deleteBoard(Long boardid);
}
