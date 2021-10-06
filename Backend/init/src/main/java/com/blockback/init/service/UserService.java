package com.blockback.init.service;


import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    User getUserByUserEmail(String userEmail);

    User getUserByUserId(Long userId);

    User createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail);

    User putUser(UserPutReq putinfo, Long userId, MultipartFile thumbnail);

    void deleteUser(Long userId);

    List<Board> getBoards();
//
//    User_Room getUserByUserNickname(String usernickname);
}
