package com.blockback.init.controller;

import com.blockback.init.common.request.BoardRegisterReq;
import com.blockback.init.common.response.BoardRes;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.common.response.PhotoRes;
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
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/{clubid}/board")
//@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<BoardRes>> searchAll(@PathVariable("clubid") Long clubid) {
        List<BoardRes> boardList = boardService.getBoardsByClubId(clubid);

        return ResponseEntity.status(200).body(boardList);
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
        Board board = boardService.getBoardByBoardId(boardid);
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
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid,
            @ApiParam(value="게시글 정보", required = true) BoardRegisterReq boardinfo,
            @RequestPart(value = "board_thumbnail", required = false) MultipartFile boardThumbnail) {

        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        if(user == null) {
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }

        User_Club_Join userclub = userClubService.getUserClubByUserIdandClubId(user.getId(), clubid);
        if (userclub == null){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }
        boardService.createBoard(user, boardinfo, clubid, boardThumbnail);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }

    @PutMapping(value = "/{boardid}")
    @ApiOperation(value = "게시글 수정")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> putBoard(
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid,
            @ApiParam(value="게시글 정보", required = true) BoardRegisterReq boardinfo)
    {
        // board의 userid가 같아야 한다.
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        Board board = boardService.getBoardByBoardId(boardid);
        if (!board.getUser().getId().equals(user.getId())){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }
        boardService.putBoard(boardinfo, boardid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }

    @DeleteMapping(value = "/{boardid}")
    @ApiOperation(value = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "작성 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MessageResponse> deleteBoard(
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid)
    {
        // board의 userid가 같아야 한다.
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        Board board = boardService.getBoardByBoardId(boardid);
        if (!board.getUser().getId().equals(user.getId())){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }
        boardService.deleteBoard(boardid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }

    @GetMapping("/photo")
    @ApiOperation(value = "게시글 전체 사진 조회")
    public ResponseEntity<PhotoRes> getPhoto(@PathVariable("clubid") Long clubid) {
        PhotoRes res = boardService.getPhotos(clubid);

        return ResponseEntity.status(200).body(res);
    }
}
