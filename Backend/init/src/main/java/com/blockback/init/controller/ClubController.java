package com.blockback.init.controller;

import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.service.ClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/club")
@Api(value = "유저 API", tags = {"Club"})
@CrossOrigin(origins = "*")
@EnableRedisHttpSession
public class ClubController {

    @Autowired
    ClubService clubService;

    @GetMapping("/")
    @ApiOperation(value = "동호회 전체 조회", notes = "동호회 전체 목록을 조회한다.")
    public ResponseEntity<List<ClubListRes>> getClubList() {
        List<ClubListRes> res = clubService.getClubList();

        return ResponseEntity.status(200).body(res);
    }
}
