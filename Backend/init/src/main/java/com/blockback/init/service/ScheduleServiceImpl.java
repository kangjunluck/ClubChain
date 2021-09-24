package com.blockback.init.service;

import com.blockback.init.common.response.ScheduleListRes;
import com.blockback.init.entity.Schedule;
import com.blockback.init.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleListRes> getScheduleList(Long clubid) {
        List<Schedule> scheduleList = scheduleRepository.findByClubId(clubid);
        List<ScheduleListRes> res = new ArrayList<>();

        for(Schedule schedule : scheduleList) {
            ScheduleListRes slr = new ScheduleListRes();
            slr.setSchdeule_id(schedule.getId());
            slr.setTitle(schedule.getTitle());
            slr.setContent(schedule.getContent());
            slr.setClub_name(schedule.getClub().getName());
            slr.setUser_id(schedule.getUser().getId());
            slr.setUser_name(schedule.getUser().getUsernickname());
            // 날짜 형식 변환
            slr.setStart(dateFormat(schedule.getStart()));
            slr.setEnd(dateFormat(schedule.getEnd()));

            res.add(slr);
        }

        return res;
    }

    public String dateFormat(Date date) { // 날짜 형식 변환
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormat.format(date);
    }
}
