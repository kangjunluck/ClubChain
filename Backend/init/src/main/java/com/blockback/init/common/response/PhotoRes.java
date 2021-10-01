package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("PhotoRes")
public class PhotoRes {

    @ApiModelProperty(name="total", example = "2")
    int total;

    @ApiModelProperty(name="list", example = "")
    List<Photos> list;
}
