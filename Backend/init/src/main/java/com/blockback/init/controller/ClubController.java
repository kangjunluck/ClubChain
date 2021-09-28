package com.blockback.init.controller;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.entity.Club;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/club")
@Api(value = "유저 API", tags = {"Club"})
//@CrossOrigin(origins = "*")
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

    @GetMapping("/{clubid}")
    @ApiOperation(value = "동호회 단일 조회", notes = "동호회 하나를 조회한다.")
    public Club getClub(@RequestParam Long clubid) {
        Club club = clubService.getClubByClubId(clubid);
        return club;
    }

    @PostMapping("/")
    @ApiOperation(value = "동호회 생성하기", notes = "동호회를 만든다.")
    public ResponseEntity<MessageResponse> createClub(@ApiIgnore HttpSession session, ClubCreatedReq req,
                                                      @RequestPart(value = "club_thumbnail", required = false) MultipartFile clubThumbnail) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 방장 정보
        User user = userService.getUserByUserEmail(owner_email);
        // 동호회 생성
        clubService.createClub(user, req, clubThumbnail);

        return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
    }

    @GetMapping("/search")
    @ApiOperation(value = "동호회 검색하기", notes = "동호회명을 검색한다.")
    public ResponseEntity<List<ClubListRes>> searchClub(@RequestParam String word) {
        List<ClubListRes> res = clubService.getClubListBySearch(word);
        return ResponseEntity.status(200).body(res);
    }

    @PutMapping("/{clubid}")
    @ApiOperation(value = "동호회 수정하기", notes = "동호회명을 수정한다.")
    public ResponseEntity<MessageResponse> modifyClub(@ApiIgnore HttpSession session, ClubCreatedReq req, @PathVariable Long clubid,
                                                      @RequestPart(value = "club_thumbnail", required = false) MultipartFile clubThumbnail) throws IOException {
        String owner_email = (String) session.getAttribute("LoginUser");
        // 방장 정보
        User user = userService.getUserByUserEmail(owner_email);

        // 동호회 수정
        if(clubService.isOwner(user, clubid) && clubService.modifyClub(req, clubThumbnail, clubid)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(400).body(MessageResponse.of(400, FAIL));
    }

    @GetMapping("/myclub")
    @ApiOperation(value = "가입한 동호회 조회", notes = "가입한 동호회 목록을 조회한다.")
    public ResponseEntity<List<ClubListRes>> getSignClubList(@ApiIgnore HttpSession session) {
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);

        List<ClubListRes> res = clubService.getSignClubList(user);
        return ResponseEntity.status(200).body(res);
    }
}
