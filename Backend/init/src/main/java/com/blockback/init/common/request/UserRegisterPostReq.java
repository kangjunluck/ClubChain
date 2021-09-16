package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
    @ApiModelProperty(name="유저 Email", example="ssafy@ssafy.com")
    String userEmail;
    @ApiModelProperty(name="유저 Password", example="1234")
    String password;
    @ApiModelProperty(name="유저 nickName", example="codingprince")
    String usernickname;
    @ApiModelProperty(name="유저 profile_thumbnail", example="/url")
    String userthumbnail;
    @ApiModelProperty(name="유저 account", example="110-257-456789")
    String useraccount;
}
