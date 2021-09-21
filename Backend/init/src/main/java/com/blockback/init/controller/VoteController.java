package com.blockback.init.controller;

import io.swagger.annotations.Api;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "투표 API", tags = {"Vote"})
@CrossOrigin(origins = "*")
@EnableRedisHttpSession
public class VoteController {
}
