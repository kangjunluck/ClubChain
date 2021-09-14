package com.blockback.init.service;

import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;
import com.blockback.init.repository.UserRepository;
import com.blockback.init.repository.UserRepositorySup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements com.blockback.init.service.UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySup userRepositorySup;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    UserRoomRepositry userRoomRepositry;

    @Override
    public User getUserByUserEmail(String userEmail) {
        User user = userRepository.findUserByUserEmail(userEmail).get();
        System.out.println(user);
        return user;
    }

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserEmail(userRegisterInfo.getUserEmail());
        // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
        user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
        user.setUsernickname(userRegisterInfo.getUsernickname());
        user.setUserthumbnail(userRegisterInfo.getUserthumbnail());

        return userRepository.save(user);
    }
//
//    @Override
//    public User modifyUser(UserModifyReq modifyinfo, String userId) {
//        User userupdate = userRepository.findUserByUserid(userId).get();
//        userupdate.setUserid(userId);
//        userupdate.setUsername(modifyinfo.getUsername());
//        userupdate.setPassword(passwordEncoder.encode(modifyinfo.getPassword()));
//
//        return userRepository.save(userupdate);
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        User user = getUserByUserId(userId);
//
//        userRepository.delete(user);
//    }
//
//    @Override
//    public User_Room getUserByUserNickname(String usernickname) {
//        User_Room user = userRepositorySup.findUserRoomByNickName(usernickname).get();
//        System.out.println(user);
//        return user;
//    }
}
