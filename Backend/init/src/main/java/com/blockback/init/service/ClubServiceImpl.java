package com.blockback.init.service;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;

    String BASE_PATH = System.getProperty("user.dir") + "/init/src/main/resources/image/club/";

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

    @Override
    public void createClub(User user, ClubCreatedReq req, MultipartFile clubThumbnail) throws IOException {

        Club club = new Club();
        club.setUser(user);
        club.setPassword(req.getPassword());
        club.setIntroduce(req.getIntroduce());
        club.setName(req.getName());
        club.setCreated(new Date());

        Club clubTmp = clubRepository.save(club);
        Long clubId = clubTmp.getId();

        String path = BASE_PATH + clubId + "-" + clubThumbnail.getOriginalFilename();
        File dest = new File(path);
        clubThumbnail.transferTo(dest);
        clubTmp.setProfile_thumbnail(getShortFilePath(path));

        clubRepository.save(clubTmp);
    }

    @Override
    public List<ClubListRes> getClubListBySearch(String name) {
        List<Club> clubList = clubRepository.findByNameContainingIgnoreCase(name);
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

    private String getShortFilePath(String path) {
        int idx = path.indexOf("image");
        return path.substring(idx, path.length());
    }

}
