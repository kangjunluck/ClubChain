package com.blockback.init.service;


import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;


public interface UserService {
    User getUserByUserEmail(String userEmail);

    User getUserById(Long id);

    User createUser(UserRegisterPostReq registerInfo);

    User putUser(UserPutReq putinfo, Long userId);

    void deleteUser(Long userId);
//
//    User_Room getUserByUserNickname(String usernickname);
}
