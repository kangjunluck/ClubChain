package com.blockback.init.service;

import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.entity.User;
import com.blockback.init.repository.UserRepository;
import com.blockback.init.repository.UserRepositorySup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public User getUserByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = null;
        if (user.isPresent()) {
            user1 = user.get();
        }
        return user1;
    }

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo, MultipartFile thumbnail) {
        String BASE_PATH = System.getProperty("user.dir") + "/image/user/";
        User user = new User();
        try {
            /*
             * 프로필 이미지 저장 순번-파일명으로 저장
             */
            if(thumbnail == null) { // 프로필 사진 입력 안함
                //프로필 이미지 저장 (기본 이미지)
                String filePath = BASE_PATH + "default.png";
                user.setUserthumbnail(filePath);
            } else { // 프로필 사진 입력함
                String filePath = BASE_PATH + userRegisterInfo.getUserEmail() + "-" + thumbnail.getOriginalFilename();
                File dest = new File(filePath);
                thumbnail.transferTo(dest);
                if (!dest.exists()) { // 파일 존재 x, 이 부분이 현재 null 값이 들어갈 수 있음, 추후 다시 해야됨
                    System.out.println("파일 업로드 실패");
                } else {
                    user.setUserthumbnail(filePath);
                }
            }
            user.setUserEmail(userRegisterInfo.getUserEmail());
            // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
            user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
            user.setUsernickname(userRegisterInfo.getUsernickname());
            // user 정보 insert
            userRepository.save(user);
            return user;

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User putUser(UserPutReq putinfo, Long userId, MultipartFile thumbnail) {
        User user = userRepository.findById(userId).get();
        String BASE_PATH = System.getProperty("user.dir") + "image/user/";
        try {
            /*
             * 프로필 이미지 저장 순번-파일명으로 저장
             */
            if(thumbnail == null) { // 프로필 사진 입력 안함
                //프로필 이미지 저장 (기본 이미지)
                File before = new File(user.getUserthumbnail());
                before.delete();
                String filePath = BASE_PATH + "default.png";
                user.setUserthumbnail(filePath);
            } else { // 프로필 사진 입력함
                File before = new File(user.getUserthumbnail());
                before.delete();
                String filePath = BASE_PATH + putinfo.getUserEmail() + "-" + thumbnail.getOriginalFilename();
                File dest = new File(filePath);
                thumbnail.transferTo(dest);
                if (!dest.exists()) { // 파일 존재 x, 이 부분이 현재 null 값이 들어갈 수 있음, 추후 다시 해야됨
                    System.out.println("파일 업로드 실패");
                } else {
                    user.setUserthumbnail(filePath);
                }
            }
            user.setUserEmail(putinfo.getUserEmail());
            user.setPassword(passwordEncoder.encode(putinfo.getPassword()));
            user.setUsernickname(putinfo.getUsernickname());
            user.setUseraccount(putinfo.getUseraccount());
            userRepository.save(user);
            return user;

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
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
