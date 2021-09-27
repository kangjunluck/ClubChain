package com.blockback.init.controller;

import com.blockback.init.common.request.CommentCreateReq;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Comment;
import com.blockback.init.entity.User;
import com.blockback.init.service.BoardService;
import com.blockback.init.service.CommentService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "댓글 API", tags = {"Commnent"})
@RestController
@RequestMapping("/api/{clubid}/{boardid}/comment")
//@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;

    @GetMapping("/")
    @ApiOperation(value = "해당 게시글의 댓글 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Comment> commentAll(
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid)
    {
        List<Comment> commentList = commentService.getCommentsList(boardid);
        return commentList;
    }

    @PostMapping("/")
    @ApiOperation(value = "해당 게시글의 댓글 작성")
    public ResponseEntity<MessageResponse> createComment(
            @ApiIgnore HttpSession session,
            CommentCreateReq commentinfo,
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid)
    {
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        Board board = boardService.getBoardByBoardId(boardid);
        commentService.createComment(commentinfo, user, board);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }

    @PutMapping("/{commentid}")
    @ApiOperation(value = "댓글 수정")
    public ResponseEntity<MessageResponse> createComment(
            @ApiIgnore HttpSession session,
            CommentCreateReq commentinfo,
            @PathVariable("clubid") Long clubid,
            @PathVariable("boardid") Long boardid,
            @PathVariable("commentid") Long commentid)
    {
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        Board board = boardService.getBoardByBoardId(boardid);
        Comment comment = commentService.getCommentByCommentId(commentid);
        if (!comment.getUser().getId().equals(user.getId())){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다"));
        }
        commentService.putComment(commentinfo, comment);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "success"));
    }
}
