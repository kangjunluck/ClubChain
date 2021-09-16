package com.blockback.init.service;

import com.blockback.init.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardsByClubId(Long clubid);
}
