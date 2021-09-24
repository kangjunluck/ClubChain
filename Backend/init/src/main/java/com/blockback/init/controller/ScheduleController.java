package com.blockback.init.controller;

import com.blockback.init.common.response.ScheduleListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.service.ClubService;
import com.blockback.init.service.ScheduleService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/{clubid}/schedule")
@Api(value = "일정 API", tags = {"Schedule"})
@EnableRedisHttpSession
public class ScheduleController {

    @Autowired
    UserService userService;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/")
    @ApiOperation(value = "동호회 일정 전체 조회", notes = "동호회 일정 전체 목록을 조회한다.")
    public ResponseEntity<List<ScheduleListRes>> scheduleList(@PathVariable("clubid") Long clubid, HttpSession session) {
        // 동호회 회원인지 확인
//        String owner_email = (String) session.getAttribute("LoginUser");
//        User user = userService.getUserByUserEmail(owner_email);

        List<ScheduleListRes> res = scheduleService.getScheduleList(clubid);
        return ResponseEntity.status(200).body(res);
    }
}
