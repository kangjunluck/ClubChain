package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ClubRes")
public class ClubRes {

    @ApiModelProperty(name="id", example = "3")
    long id;

    @ApiModelProperty(name="name", example = "동호회명")
    String name;

    @ApiModelProperty(name="introduce", example = "동호회 소개")
    String introduce;

    @ApiModelProperty(name="profile_thumbnail", example = "/image/~")
    String profile_thumbnail;

    @ApiModelProperty(name="clubaccount", example = "")
    String clubaccount;

    @ApiModelProperty(name="password", example = "1234")
    String password;

    @ApiModelProperty(name="created", example = "2021-09-21")
    String created;

    @ApiModelProperty(name="owner_name", example = "ssafy")
    String owner_name;

    @ApiModelProperty(name="join_num", example = "3")
    int join_num;
}
