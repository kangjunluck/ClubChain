package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardRes")
public class BoardRes {

    @ApiModelProperty(name="clubid", example = "3")
    long clubid;

    @ApiModelProperty(name="id", example = "3")
    long id;

    @ApiModelProperty(name="created", example = "2010-09-10")
    String created;

    @ApiModelProperty(name="view", example = "2")
    int view;

    @ApiModelProperty(name="introduce", example = "글내용")
    String content;

    @ApiModelProperty(name="title", example = "글제목")
    String title;

    @ApiModelProperty(name="section", example = "분류")
    String section;

    @ApiModelProperty(name="photo_thumbnail", example = "")
    String photo_thumbnail;

    @ApiModelProperty(name="usernickname", example = "닉네임")
    String usernickname;

    @ApiModelProperty(name="userEmail", example = "닉네임")
    String userEmail;

    @ApiModelProperty(name="password", example = "닉네임")
    String password;

    @ApiModelProperty(name="userthumbnail", example = "닉네임")
    String userthumbnail;

    @ApiModelProperty(name="useraccount", example = "닉네임")
    String useraccount;

    @ApiModelProperty(name="userid", example = "닉네임")
    long userid;
}
