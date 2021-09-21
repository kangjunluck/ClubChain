package com.blockback.init.controller;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.entity.User;
import com.blockback.init.service.ClubService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/club")
@Api(value = "유저 API", tags = {"Club"})
@CrossOrigin(origins = "*")
@EnableRedisHttpSession
public class ClubController {

    @Autowired
    ClubService clubService;

    @Autowired
    UserService userService;

    String SUCCESS = "Success";
    String FAIL = "Fail";

    @GetMapping("/")
    @ApiOperation(value = "동호회 전체 조회", notes = "동호회 전체 목록을 조회한다.")
    public ResponseEntity<List<ClubListRes>> getClubList() {
        List<ClubListRes> res = clubService.getClubList();

        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/")
    @ApiOperation(value = "동호회 생성하기", notes = "동호회를 만든다.", consumes = "multipart/form-data", produces = "multipart/form-data")
    public ResponseEntity<MessageResponse> createClub(HttpSession session, ClubCreatedReq req,
                  @RequestPart(value = "study_thumbnail", required = false) MultipartFile clubThumbnail) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");

        // 방장 정보
        User user = userService.getUserByUserEmail(owner_email);
        // 동호회 생성
        clubService.createClub(user, req, clubThumbnail);

        return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
    }

    @GetMapping("/search/{word}")
    @ApiOperation(value = "동호회 검색하기", notes = "동호회명을 검색한다.")
    public ResponseEntity<List<ClubListRes>> searchClub(@RequestParam String word) {
        List<ClubListRes> res = clubService.getClubListBySearch(word);
        return ResponseEntity.status(200).body(res);
    }
}