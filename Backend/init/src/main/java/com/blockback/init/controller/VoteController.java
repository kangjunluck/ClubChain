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

@RestController
@RequestMapping("/api")
@Api(value = "투표 API", tags = {"Vote"})
@CrossOrigin(origins = "*")
@EnableRedisHttpSession
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

        if(voteService.createVote(clubid, req, user)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(500).body(MessageResponse.of(500, FAIL));
    }
}
