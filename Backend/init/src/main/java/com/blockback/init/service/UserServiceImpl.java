package com.blockback.init.service;

import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;
import com.blockback.init.repository.UserRepository;
import com.blockback.init.repository.UserRepositorySup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
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
        Optional<User> user = userRepository.findUserByUserEmail(userEmail);
        User user1 = null;
        if (user.isPresent()) {
            user1 = user.get();
        }
        return user1;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User user1 = null;
        if (user.isPresent()) {
            user1 = user.get();
        }
        return user1;
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

    @Override
    public User putUser(UserPutReq putinfo, Long userId) {
        User user = userRepository.findById(userId).get();
        user.setUserEmail(putinfo.getUserEmail());
        user.setPassword(putinfo.getPassword());
        user.setUsernickname(putinfo.getUsernickname());
        user.setUserthumbnail(putinfo.getUserthumbnail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }
//
//    @Override
//    public User_Room getUserByUserNickname(String usernickname) {
//        User_Room user = userRepositorySup.findUserRoomByNickName(usernickname).get();
//        System.out.println(user);
//        return user;
//    }
}
