package com.blockback.init.common.response;


import com.blockback.init.entity.User;
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

    public static UserResponse of(Integer statusCode, String message, User user) {
        UserResponse res = new UserResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserEmail(user.getUserEmail());
        res.setUsernickname(user.getUsernickname());
        res.setUserPk(user.getId());
        res.setUserthumbnail(user.getUserthumbnail());
        return res;
    }

}
