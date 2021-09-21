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
    @ApiModelProperty(name="userid")
    Long userid;
    @ApiModelProperty(name="userEmail", example = "ssafy@ssary.com")
    String userEmail;
    @ApiModelProperty(name="usernickname", example = "피싸")
    String usernickname = null;
    @ApiModelProperty(name="userthumbnail", example = "/url")
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
        res.setUserid(user.getId());
        res.setUserthumbnail(user.getUserthumbnail());
        return res;
    }

}
