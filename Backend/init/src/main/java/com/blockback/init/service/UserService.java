package com.blockback.init.service;


import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    User getUserByUserEmail(String userEmail);

    User getUserByUserId(Long userId);

    User createUser(UserRegisterPostReq registerInfo, MultipartFile thumbnail);

    User putUser(UserPutReq putinfo, Long userId, MultipartFile thumbnail);

    void deleteUser(Long userId);
//
//    User_Room getUserByUserNickname(String usernickname);
}
