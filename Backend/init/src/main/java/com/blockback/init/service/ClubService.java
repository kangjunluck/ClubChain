package com.blockback.init.service;

import com.blockback.init.common.request.ClubCreatedReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClubService {

    List<ClubListRes> getClubList();
    void createClub(User user, ClubCreatedReq req, MultipartFile clubThumbnail) throws IOException;
    List<ClubListRes> getClubListBySearch(String name);
    boolean modifyClub(ClubCreatedReq req, MultipartFile clubThumbnail, Long clubid);
    boolean isOwner(User user, Long clubid);
    List<ClubListRes> getSignClubList(User user);
}
