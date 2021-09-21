package com.blockback.init.controller;

import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.response.Response;
import com.blockback.init.entity.Board;
import com.blockback.init.service.BoardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/{clubid}/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/")
    @ApiOperation(value = "게시글 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Board> searchAll(@PathVariable("clubid") Long clubid)
    {
        List<Board> boardList = boardService.getBoardsByClubId(clubid);
        return boardList;
    }

    @GetMapping(value = "/{boardid}")
    @ApiOperation(value = "게시글 단일 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "글 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public Board search(
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid )
    {
        Board board = boardService.getBoardByBoardId(clubid, boardid);
        return board;
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "게시글 작성")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Response> createBoard(
            @PathVariable("clubid") Long clubid,
            @ApiParam(value="게시글 정보", required = true) BoardRegisterReq boardinfo)
    {
        //사용자이면서 동호회 가입 이용자만 글을 작성할 수 있어야 한다. 여기에 조건을 넣어주자. 수정중
        Board board = boardService.createBoard(boardinfo, clubid);
        return null;
    }


}
