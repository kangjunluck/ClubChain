package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("VoteListRes")
public class VoteListRes {

    @ApiModelProperty(name="voteid", example = "3")
    long voteid;

    @ApiModelProperty(name="content", example = "투표 내용")
    String content;

    @ApiModelProperty(name="username", example = "김싸피")
    String username;

    @ApiModelProperty(name="clubid", example = "1")
    Long clubid;

    @ApiModelProperty(name="clubname", example = "이게 블록이야")
    String clubname;

    @ApiModelProperty(name="vote_list", example = "")
    List<VoteItemList> vote_list;
}
