package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VoteItemList")
public class VoteItemList {

    @ApiModelProperty(name="itemid", example = "3")
    long itemid;

    @ApiModelProperty(name="items", example = "초코")
    String items;

    @ApiModelProperty(name="votes", example = "4")
    Long votes;
}
