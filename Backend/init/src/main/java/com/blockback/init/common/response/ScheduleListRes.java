package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("ScheduleListRes")
public class ScheduleListRes {

    @ApiModelProperty(name="schdeuleid", example = "3")
    long schdeule_id;

    @ApiModelProperty(name="title", example = "일정명")
    String title;

    @ApiModelProperty(name="content", example = "일정 내용")
    String content;

    @ApiModelProperty(name="club_name", example = "블록 동호회")
    String club_name;

    @ApiModelProperty(name="user_id", example = "3")
    long user_id;

    @ApiModelProperty(name="user_name", example = "닉네임")
    String user_name;

    @ApiModelProperty(name="start", example = "2021-09-30")
    String start;

    @ApiModelProperty(name="end", example = "2021-10-05")
    String end;
}
