package com.blockback.init.controller;

import com.blockback.init.common.request.VoteCreateReq;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.common.response.VoteListRes;
import com.blockback.init.entity.User;
import com.blockback.init.service.UserService;
import com.blockback.init.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "투표 API", tags = {"Vote"})
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    UserService userService;

    String SUCCESS = "Success";
    String FAIL = "Fail";

    @GetMapping("/{clubid}/vote")
    @ApiOperation(value = "투표 전체 조회", notes = "투표 전체 목록을 조회한다.")
    public ResponseEntity<List<VoteListRes>> getVoteList(@RequestParam Long clubid) {
        List<VoteListRes> res = voteService.getVoteList(clubid);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/{clubid}/vote")
    @ApiOperation(value = "투표 만들기", notes = "투표를 만든다.")
    public ResponseEntity<MessageResponse> createVote(@RequestParam Long clubid, HttpSession session,
                                                        @RequestBody VoteCreateReq req) {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 투표 생성자 정보
        User user = userService.getUserByUserEmail(owner_email);

        if(user != null && voteService.createVote(clubid, req, user)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
    }

    @PostMapping("/{clubid}/vote/{voteid}")
    @ApiOperation(value = "투표 하기", notes = "투표를 한다.")
    public ResponseEntity<MessageResponse> vote(@RequestParam Long clubid, @RequestParam Long voteid,
                                                HttpSession session, @RequestBody Map<String, Long> req) {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 투표 생성자 정보
        User user = userService.getUserByUserEmail(owner_email);

        if(!req.containsKey("itemid")) {
            return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
        }

        Long itemId = req.get("itemid");
        if(voteService.vote(voteid, user, itemId)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
    }

    @PutMapping("/{clubid}/vote/{voteid}")
    @ApiOperation(value = "재투표 하기", notes = "재투표를 한다.")
    public ResponseEntity<MessageResponse> revote(@RequestParam Long clubid, @RequestParam Long voteid,
                                                  HttpSession session, @RequestBody Map<String, Long> req) {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 투표 생성자 정보
        User user = userService.getUserByUserEmail(owner_email);

        if(!req.containsKey("itemid")) {
            return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
        }

        Long itemId = req.get("itemid");
        if(voteService.revote(voteid, user, itemId)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
    }

    @DeleteMapping("/{clubid}/vote/{voteid}")
    @ApiOperation(value = "투표 삭제", notes = "투표를 삭제한다.")
    public ResponseEntity<MessageResponse> voteDelete(@RequestParam Long clubid, @RequestParam Long voteid, HttpSession session) {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 투표 생성자 정보
        User user = userService.getUserByUserEmail(owner_email);

        // 직접 만들었는지 확인
        if(!voteService.isOwner(user, voteid)) {
            return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
        }

        voteService.voteDelete(voteid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
    }
}
