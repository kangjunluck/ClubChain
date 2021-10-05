package com.blockback.init.controller;


import com.blockback.init.common.request.EmailReq;
import com.blockback.init.common.request.UserLoginReq;
import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.common.response.MessageResponse;
import com.blockback.init.common.response.UserResponse;
import com.blockback.init.entity.User;
import com.blockback.init.service.EmailService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping(value = "/api/users")
@Api(value = "유저 API", tags = {"User"})
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    private HashMap<String, String> emailRepository = new HashMap<>();

    String SUCCESS = "Success";
    String FAIL = "Fail";

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public ResponseEntity<UserResponse> login(
            @ApiParam(value="로그인 정보", required = true) UserLoginReq loginInfo,
            @ApiIgnore HttpSession session) {
        String userEmail = loginInfo.getUserEmail();
        String password = loginInfo.getPassword();
        User user = userService.getUserByUserEmail(userEmail);
        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if (user == null)
        {
            return ResponseEntity.status(404).body(UserResponse.of(404, "존재하지 않는 계정입니다."));
        }
        if(passwordEncoder.matches(password, user.getPassword())) {
//            JwtTokenUtil.getToken(userEmail);
            // 세션에 저장해준다.
            session.setAttribute("LoginUser", userEmail);
            return ResponseEntity.ok(UserResponse.of(200, "Success", user));
        }
        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(401).body(UserResponse.of(401, "잘못된 비밀번호입니다."));
    }
    @DeleteMapping("/logout")
    @ApiOperation(value = "로그아웃")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
    })
    public ResponseEntity<UserResponse> logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(UserResponse.of(200, "Success"));
    }


    @PostMapping("/regist")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserResponse> register(
            @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo,
            @RequestPart(value = "image", required = false) MultipartFile thumbnail) {
        User user = userService.getUserByUserEmail(registerInfo.getUserEmail());
        if (user != null) {
            return ResponseEntity.status(401).body(UserResponse.of(401, "이미 있는 유저입니다"));
        }
        userService.createUser(registerInfo, thumbnail);
        return ResponseEntity.ok(UserResponse.of(200, "Success"));
    }

    @PutMapping(value="/{userId}")
    @ApiOperation(value = "회원정보 수정", notes = "회원정보를 수정합니다.")
    public ResponseEntity<UserResponse> modifyUser(
            @PathVariable("userId") Long userId,
            @ApiParam(value="회원가입 정보", required = true) UserPutReq putinfo,
            @RequestPart(value = "image", required = false) MultipartFile thumbnail) {
        User user = userService.putUser(putinfo, userId, thumbnail);

        return ResponseEntity.status(201).body(UserResponse.of(201, "Success", user));
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제합니다. ")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
    })
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(204).body(UserResponse.of(204, "Success"));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "회원정보 조회", notes = "회원정보를 조회합니다.")
    public ResponseEntity<UserResponse> UserInfo(
            @PathVariable("userId") String userEmail) {

        System.out.println("회원정보 조회");
        User user = userService.getUserByUserEmail(userEmail);

        if (user == null)
        {
            return ResponseEntity.status(404).body(UserResponse.of(404, "존재하지 않는 아이디입니다."));
        }
        else {
            return ResponseEntity.status(200).body(UserResponse.of(200, "Success", user));
        }
    }

    @GetMapping("/email/{userEmail}")
    @ApiOperation(value = "이메일 인증 요청", notes = "이메일 인증 요청을 보냅니다.")
    public ResponseEntity<MessageResponse> sendEmail(@PathVariable String userEmail) throws Exception {

        String code = emailService.sendEmail(userEmail);

        if(code != null) {
            emailRepository.put(userEmail, code);
        }

        return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
    }

    @PostMapping("/verifyCode")
    @ApiOperation(value = "이메일 인증 코드 검증", notes = "이메일 인증 코드 검증하기.")
    public ResponseEntity<MessageResponse> verifyEmailCode(@RequestBody EmailReq emailReq) {

        if (emailReq.getCode().length() != 0 && emailReq.getCode().equals(emailRepository.get(emailReq.getEmail()))) {
            emailRepository.remove(emailReq.getEmail());
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(400).body(MessageResponse.of(400, FAIL));
    }

    @GetMapping("/email/clear")
    @ApiOperation(value = "이메일 hashMap 내용 삭제", notes = "hashMap 내용 전체 삭제. 이메일 인증 테스트하면서 중간에 emailRepository를 지울때 사용.")
    public ResponseEntity<MessageResponse> clearEmail() {
        emailRepository.clear();
        return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
    }

    @GetMapping("/password/{userEmail}")
    @ApiOperation(value = "비밀번호 찾기", notes = "비밀번호 찾기")
    public ResponseEntity<MessageResponse> findPassword(@PathVariable String userEmail) throws Exception {

        User user = userService.getUserByUserEmail(userEmail);
        if(user != null && emailService.findPassowrd(user)) {
            return ResponseEntity.status(200).body(MessageResponse.of(200, SUCCESS));
        }

        return ResponseEntity.status(400).body(MessageResponse.of(400, FAIL));
    }

    @GetMapping("/islogin")
    @ApiOperation(value = "로그인여부확인", notes = "session이 있는지 없는지 확인")
    public ResponseEntity<UserResponse> isLogin(@ApiIgnore HttpSession session) throws Exception {
        String owner_email = (String) session.getAttribute("LoginUser");
        User user = userService.getUserByUserEmail(owner_email);
        System.out.println(owner_email);
        if(user != null) {
            return ResponseEntity.status(200).body(UserResponse.of(200, "Success", user));
        }
        return ResponseEntity.status(400).body(UserResponse.of(200, "Success", user));
    }
}
