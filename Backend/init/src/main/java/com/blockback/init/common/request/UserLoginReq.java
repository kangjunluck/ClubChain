package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostRequest")
public class UserLoginReq {
    @ApiModelProperty(name="userEmail", example="ssafy@ssafy.com")
    String userEmail;
    @ApiModelProperty(name="password", example="1234")
    String password;
}
