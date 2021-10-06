package com.blockback.init.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Photos {

    @ApiModelProperty(name="photo_address", example = "/image/~")
    String photo_address;

    @ApiModelProperty(name="user_name", example = "아무개")
    String user_name;

    @ApiModelProperty(name="userthumbnail", example = "image/~")
    String userthumbnail;

    @ApiModelProperty(name="time", example = "")
    String time;
}
