package com.blockback.init.service;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.UserClubRepository;
import com.blockback.init.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserClubRepository userClubRepository;

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
    public Club getClubByClubId(Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        if (club.isPresent()) {
            return club.get();
        }
        return null;
    }

    @Override
    public void createClub(User user, ClubCreatedReq req, MultipartFile clubThumbnail) throws IOException {

        Club club = new Club();
        club.setUser(user);
        club.setPassword(req.getPassword());
        club.setIntroduce(req.getIntroduce());
        club.setName(req.getName());
        club.setCreated(new Date());
        club.setClubaccount((req.getClubaccount()));

        Club clubTmp = clubRepository.save(club);
        Long clubId = clubTmp.getId();

        String path = BASE_PATH;
        if(clubThumbnail == null) {
            path += "default.png";

        } else {
            path += clubId + "-" + clubThumbnail.getOriginalFilename();
            File dest = new File(path);
            clubThumbnail.transferTo(dest);
        }
        clubTmp.setProfile_thumbnail(getShortFilePath(path));

        clubRepository.save(clubTmp);

        // 동호회 폴더 생성
        String club_path = BASE_PATH + clubId;
        File createFolder = new File(club_path);
        if(!createFolder.exists()) {
            createFolder.mkdirs();
        }

        User_Club_Join ucj = new User_Club_Join();
        ucj.setPosition("관리자");
        ucj.setUser(user);
        ucj.setClub(clubTmp);
        userClubRepository.save(ucj);
    }

    @Override
    public boolean modifyClub(ClubCreatedReq req, MultipartFile clubThumbnail, Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        if(!club.isPresent()) {
            return false;
        }

        club.get().setPassword(req.getPassword());
        club.get().setIntroduce(req.getIntroduce());
        club.get().setName(req.getName());

        try {
            String originPath = club.get().getProfile_thumbnail();
            if(clubThumbnail != null) { // 입력이 있을 때
                if(!originPath.equals("image/club/default.png")) { // 디폴트가 아니면 삭제해주기
                    String dpath = BASE_PATH + originPath.substring(11);
                    Path deleteFilePath = Paths.get(dpath);
                    Files.deleteIfExists(deleteFilePath);
                }

                String newPath = BASE_PATH + club.get().getId() + "-" + clubThumbnail.getOriginalFilename();
                File dest = new File(newPath);
                clubThumbnail.transferTo(dest);
                if(!dest.exists()) {
                    System.out.println("파일 업로드 실패");
                }else {
                    club.get().setProfile_thumbnail(getShortFilePath(newPath));
                }
            }

            clubRepository.save(club.get());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOwner(User user, Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        if(!club.isPresent()) {
            return false;
        }

        if(user.getUserEmail().equals(club.get().getUser().getUserEmail())) {
            return true;
        }
        return false;
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

    @Override
    public List<ClubListRes> getSignClubList(User user) {
        List<User_Club_Join> signList = userClubRepository.findByUser(user);
        List<ClubListRes> res = new ArrayList<>();
        for(User_Club_Join sign : signList) {
            ClubListRes tmp = new ClubListRes();
            Club club = sign.getClub();
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
