package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
    @ApiModelProperty(name="유저 Email", example="ssafy_web@ssafy.com")
    String userEmail;
    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;
    @ApiModelProperty(name="유저 nickName", example="codingprince")
    String usernickname;
    @ApiModelProperty(name="유저 profile_thumbnail", example="/url")
    String userthumbnail;
}
