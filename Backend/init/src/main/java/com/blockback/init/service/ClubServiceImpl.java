package com.blockback.init.service;

import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    @Override
    public List<ClubListRes> getClubList() {
        List<Club> clubList = clubRepository.findAll();
        List<ClubListRes> res = new ArrayList<>();

        for(Club club : clubList) {
            ClubListRes tmp = new ClubListRes();

            tmp.setClubid(club.getId());
            tmp.setName(club.getName());
            tmp.setIntroduce(club.getIntroduce());
            tmp.setProfile_thumbnail(club.getProfile_thumbnail());
            tmp.setPassword(club.getPassword());
            tmp.setOwner_id(club.getUser().getId());
            tmp.setOwner_name(club.getUser().getUsernickname());

            res.add(tmp);
        }

        return res;
    }
}
