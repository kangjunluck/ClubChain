package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ClubCreatedReq")
public class ClubCreatedReq {

    @ApiModelProperty(name="name", example="이게 블록이야")
    String name;

    @ApiModelProperty(name="introduce", example="블록체인 학습 돟호회")
    String introduce;

    @ApiModelProperty(name="password", example="1234")
    String password;

    @ApiModelProperty(name="clubaccount", example="21s2d1f5a")
    String clubaccount;
}
