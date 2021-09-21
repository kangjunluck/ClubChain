package com.blockback.init.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Response")
public class Response {
    @ApiModelProperty(name="응답 메시지", example = "정상")
    String message = null;
    @ApiModelProperty(name="응답 코드", example = "200")
    Integer statusCode = null;

    public static Response of(Integer statusCode, String message) {
        Response res = new Response();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
