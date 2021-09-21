package com.blockback.init.controller;

import com.blockback.init.common.response.VoteListRes;
import com.blockback.init.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "투표 API", tags = {"Vote"})
@CrossOrigin(origins = "*")
@EnableRedisHttpSession
public class VoteController {

    @Autowired
    VoteService voteService;

    @GetMapping("/{clubid}/vote")
    @ApiOperation(value = "투표 전체 조회", notes = "투표 전체 목록을 조회한다.")
    public ResponseEntity<List<VoteListRes>> getVoteList(@RequestParam Long clubid) {
        List<VoteListRes> res = voteService.getVoteList(clubid);
        return ResponseEntity.status(200).body(res);
    }
}
