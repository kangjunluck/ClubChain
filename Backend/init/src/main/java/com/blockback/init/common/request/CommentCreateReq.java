package com.blockback.init.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentCreateRequest")
public class CommentCreateReq {
    @ApiModelProperty(name="content", example="반갑습니다")
    String content;
}
