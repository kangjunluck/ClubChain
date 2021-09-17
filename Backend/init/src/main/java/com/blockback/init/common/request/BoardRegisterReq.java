package com.blockback.init.common.request;

import com.blockback.init.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardRegisterRequest")
public class BoardRegisterReq {
    @ApiModelProperty(name="id", example="1")
    Long id;
    @ApiModelProperty(name="title", example="안녕하세요")
    String title;
    @ApiModelProperty(name="content", example="반갑습니다")
    String content;
    @ApiModelProperty(name="userid", example="1")
    Long userid;
    @ApiModelProperty(name = "section", example="분류")
    String section;
}
