package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("ScheduleCreateReq")
public class ScheduleCreateReq {

    @ApiModelProperty(name="title", example = "제목")
    String title;

    @ApiModelProperty(name="content", example = "내용")
    String content;

    @ApiModelProperty(name="start", example = "시작시간")
    String start;

    @ApiModelProperty(name="end", example = "종료시간")
    String end;
}
