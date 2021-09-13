package com.blockback.init.common.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class UserResponse {
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;
    @ApiModelProperty(name="User PK")
    Long userPk;
    @ApiModelProperty(name="유저 Email", example = "ssafy@ssary.com")
    String userEmail;
    @ApiModelProperty(name="유저 닉네임", example = "피싸")
    String usernickname = null;
    @ApiModelProperty(name="유저 프로필 사진", example = "/url")
    String userthumbnail = null;

    public static UserResponse of(Integer statusCode, String message) {
        UserResponse res = new UserResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

    public static UserResponse of(Integer statusCode, String message, String userEmail,String usernickname) {
        UserResponse res = new UserResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserEmail(userEmail);
        res.setUsernickname(usernickname);

        return res;
    }

//    public static UserResponse of(Integer statusCode, String message) {
//        UserResponse body = new UserResponse();
//        body.message = message;
//        body.statusCode = statusCode;
//        return body;
//    }

//    public static UserResponse of(String username, String usernickname) {
//        UserResponse body = new UserResponse();
//        body.username = username;
//        body.usernickname = usernickname;
//        return body;
//    }
//
//    public static UserResponse of(User user) {
//        UserResponse res = new UserResponse();
//        res.setUserId(user.getUseremail());
//        res.setUserPk(user.getId());
//        res.setUsername(user.getUsername());
//        return res;
//    }
}
