package com.blockback.init.controller;

import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;
import com.blockback.init.service.BoardService;
import com.blockback.init.service.ClubService;
import com.blockback.init.service.UserClubService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/{clubid}/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    ClubService clubService;
    @Autowired
    UserClubService userClubService;

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
            @PathVariable("boardid") Long boardid)
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
    public ResponseEntity<MessageResponse> createBoard(
            HttpSession session,
            @PathVariable("clubid") Long clubid,
            @ApiParam(value="게시글 정보", required = true) BoardRegisterReq boardinfo)
    {
        //사용자이면서 동호회 가입 이용자만 글을 작성할 수 있어야 한다. 여기에 조건을 넣어주자. 수정중
        // User_Club_Join 을 만들고 확인해야 한다. 아직 동호회 가입에 대한 것이 안만듬
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        User_Club_Join userclub = userClubService.getUserClubByUserIdandClubId(user.getId(), clubid);
        if (user == null | userclub == null){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }
        boardService.createBoard(user, boardinfo, clubid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }

}
