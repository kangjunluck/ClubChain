package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostRequest")
public class UserLoginReq {
    @ApiModelProperty(name="유저 email", example="ssafy_web@ssafy.com")
    String useremail;
    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;
}
