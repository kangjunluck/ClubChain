package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ApiModel("VoteCreateReq")
public class VoteCreateReq {

    @ApiModelProperty(name="content", example = "오이 vs 민트초코")
    String content;

    @ApiModelProperty(name="end", example = "2021-09-08")
    String end;

    @ApiModelProperty(name="vote_list", example = "")
    ArrayList<String> vote_list;
}
