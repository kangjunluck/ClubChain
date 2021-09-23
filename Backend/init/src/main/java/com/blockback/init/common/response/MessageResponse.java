package com.blockback.init.common.response;

import com.blockback.init.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MessageResponse")
public class MessageResponse {
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;


    public static MessageResponse of(Integer statusCode, String message) {
        MessageResponse body = new MessageResponse();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
    public static MessageResponse of(Integer statusCode, String message, User user) {
        MessageResponse body = new MessageResponse();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
