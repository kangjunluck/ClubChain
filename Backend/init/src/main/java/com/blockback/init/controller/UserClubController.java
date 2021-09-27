package com.blockback.init.controller;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;
import com.blockback.init.service.ClubService;
import com.blockback.init.service.UserClubService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/{clubid}")
@Api(value = "User Club API", tags = {"User Club"})
//@CrossOrigin(origins = "*")
public class UserClubController {
    @Autowired
    ClubService clubService;

    @Autowired
    UserService userService;

    @Autowired
    UserClubService userClubService;

    @GetMapping("/")
    @ApiOperation(value = "동호회 입장 가능 확인", notes = "동호회 가입 유저인지 확인한다.")
    public ResponseEntity<MessageResponse> checkJoin(
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 유저 정보
        User user = userService.getUserByUserEmail(owner_email);
        User_Club_Join userclub = userClubService.getUserClubByUserIdandClubId(user.getId(), clubid);
        if (userclub == null){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "동호회를 가입해주세요"));
        }
        return ResponseEntity.status(200).body(MessageResponse.of(200, "동호회 유저입니다"));
    }

    @PostMapping("/")
    @ApiOperation(value = "동호회 가입하기", notes = "동호회를 가입한다.")
    public ResponseEntity<MessageResponse> signClub(
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 유저 정보
        User user = userService.getUserByUserEmail(owner_email);
        // 유저가 해당 동호회 가입여부 확인
        User_Club_Join userclub = userClubService.getUserClubByUserIdandClubId(user.getId(), clubid);
        if (userclub != null){
            return ResponseEntity.status(401).body(MessageResponse.of(401, "잘못된 요청입니다."));
        }
        // 동호회 가입
        userClubService.signClub(user, clubid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "Success"));
    }

    @DeleteMapping("/")
    @ApiOperation(value = "동호회 탈퇴하기", notes = "동호회를 탈퇴한다.")
    public ResponseEntity<MessageResponse> reSignClub(
            @ApiIgnore HttpSession session,
            @PathVariable("clubid") Long clubid) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 유저 정보
        User user = userService.getUserByUserEmail(owner_email);
        userClubService.resignClub(user, clubid);
        return ResponseEntity.status(200).body(MessageResponse.of(200, "Success"));
    }

}
