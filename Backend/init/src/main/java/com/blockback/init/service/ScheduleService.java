package com.blockback.init.service;

import com.blockback.init.common.response.ScheduleListRes;

import java.util.List;

public interface ScheduleService {
    List<ScheduleListRes> getScheduleList(Long clubid);
}
