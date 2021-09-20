package com.blockback.init.common.response;

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

    public MessageResponse() {}

    public MessageResponse(Integer statusCode){
        this.statusCode = statusCode;
    }

    public MessageResponse(Integer statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public static MessageResponse of(Integer statusCode, String message) {
        MessageResponse body = new MessageResponse();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }

}
