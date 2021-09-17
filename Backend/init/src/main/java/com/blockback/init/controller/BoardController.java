package com.blockback.init.controller;

import com.blockback.init.entity.Board;
import com.blockback.init.service.BoardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "게시판 API", tags = {"Article"})
@RestController
@RequestMapping("/api/{clubid}/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    @ApiOperation(value = "게시글 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Board> index(@PathVariable("clubid") Long clubid)
    {
        List<Board> boardList = boardService.getBoardsByClubId(clubid);
        return boardList;
    }
}
