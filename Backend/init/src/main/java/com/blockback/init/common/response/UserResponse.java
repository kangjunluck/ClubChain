package com.blockback.init.common.response;


import com.blockback.init.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ApiModelProperty(name="userEmail")
    String userEmail;
    @ApiModelProperty(name="useraccount")
    String useraccount;
    @ApiModelProperty(name="userprivatekey")
    String userprivatekey;
    @ApiModelProperty(name="userthumbnail")
    String userthumbnail;
    @ApiModelProperty(name="usernickname")
    String usernickname;


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
        res.setUseraccount(user.getUseraccount());
        res.setUserprivatekey(user.getUserprivatekey());
        res.setUserthumbnail(user.getUserthumbnail());
        res.setUsernickname(user.getUsernickname());
        return res;
    }
}
