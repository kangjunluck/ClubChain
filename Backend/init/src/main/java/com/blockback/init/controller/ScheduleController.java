package com.blockback.init.controller;

import com.blockback.init.common.response.ScheduleListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.service.ClubService;
import com.blockback.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/{clubid}/schedule")
@Api(value = "일정 API", tags = {"Schedule"})
@EnableRedisHttpSession
public class ScheduleController {


}
