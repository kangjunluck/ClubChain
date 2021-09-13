package com.blockback.init.service;


import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;



public interface UserService {
    User getUserByUserEmail(String userEmail);

    User createUser(UserRegisterPostReq registerInfo);
//
//    User modifyUser(UserModifyReq modifyinfo, String userId);
//
//    void deleteUser(String userId);
//
//    User_Room getUserByUserNickname(String usernickname);
}
