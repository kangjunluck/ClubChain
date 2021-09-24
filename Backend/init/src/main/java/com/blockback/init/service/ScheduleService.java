package com.blockback.init.service;

import com.blockback.init.common.request.ScheduleCreateReq;
import com.blockback.init.common.response.ScheduleListRes;
import com.blockback.init.entity.User;

import java.util.List;

public interface ScheduleService {
    List<ScheduleListRes> getScheduleList(Long clubid);
    boolean createSchedule(Long clubid, User user, ScheduleCreateReq req);
}
