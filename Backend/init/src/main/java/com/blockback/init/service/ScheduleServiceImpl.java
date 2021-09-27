package com.blockback.init.service;

import com.blockback.init.common.request.ScheduleCreateReq;
import com.blockback.init.common.response.ScheduleListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.Schedule;
import com.blockback.init.entity.User;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ClubRepository clubRepository;

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

    @Override
    public boolean createSchedule(Long clubid, User user, ScheduleCreateReq req) {
        Optional<Club> club = clubRepository.findById(clubid);
        if(!club.isPresent()) {
            return false;
        }

        Schedule schedule = new Schedule();
        schedule.setContent(req.getContent());
        schedule.setEnd(stringFormatToDate(req.getEnd()));
        schedule.setStart(stringFormatToDate(req.getStart()));
        schedule.setTitle(req.getTitle());
        schedule.setUser(user);
        schedule.setClub(club.get());

        scheduleRepository.save(schedule);
        return true;
    }

    @Override
    public boolean modifySchedule(User user, Long scheduleid, ScheduleCreateReq req) {
        // 작성자인지 확인
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleid);
        if(!schedule.isPresent() || !schedule.get().getUser().getUserEmail().equals(user.getUserEmail())) {
            return false;
        }

        // 수정하기
        schedule.get().setTitle(req.getTitle());
        schedule.get().setStart(stringFormatToDate(req.getStart()));
        schedule.get().setContent(req.getContent());
        schedule.get().setEnd(stringFormatToDate(req.getEnd()));
        scheduleRepository.save(schedule.get());

        return true;
    }

    @Override
    public boolean deleteSchedule(User user, Long scheduleid) {
        // 작성자인지 확인
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleid);
        if(!schedule.isPresent() || !schedule.get().getUser().getUserEmail().equals(user.getUserEmail())) {
            return false;
        }

        // 삭제하기
        scheduleRepository.deleteById(scheduleid);

       return true;
    }

    public String dateFormat(Date date) { // 날짜 형식 변환
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormat.format(date);
    }

    public Date stringFormatToDate(String date) { // 날짜 형식 변환
        Date res = new Date();
        try {
            res = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return res;
    }
}
