package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ClubListRes")
public class ClubListRes {

    @ApiModelProperty(name="clubid", example = "3")
    long clubid;

    @ApiModelProperty(name="name", example = "이게 블록이야!")
    String name;

    @ApiModelProperty(name="introduce", example = "대전 1반 2팀이다.")
    String introduce;

    @ApiModelProperty(name="profile_thumbnail", example = "/image/default.png")
    String profile_thumbnail;

    @ApiModelProperty(name="password", example = "1234")
    String password;

    @ApiModelProperty(name="owner_id", example = "1")
    long owner_id;

    @ApiModelProperty(name="owner_name", example = "ssafy")
    String owner_name;
}
