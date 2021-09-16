package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@ApiModel("UserPutRequest")
public class UserPutReq  {
    @ApiModelProperty(name="userEmail", example="ssafy@ssafy.com")
    String userEmail;
    @ApiModelProperty(name="password", example="1234")
    String password;
    @ApiModelProperty(name="usernickname", example="codingprince")
    String usernickname;
    @ApiModelProperty(name="useraccount", example="110-257-456789")
    String useraccount;
}
