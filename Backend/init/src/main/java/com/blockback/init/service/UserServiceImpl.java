package com.blockback.init.service;

import com.blockback.init.common.request.UserPutReq;
import com.blockback.init.common.request.UserRegisterPostReq;
import com.blockback.init.common.response.ClubListRes;
import com.blockback.init.entity.Board;
import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.repository.BoardRepository;
import com.blockback.init.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUserEmail(String userEmail) {
        Optional<User> user = userRepository.findByUserEmail(userEmail);
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
        String BASE_PATH = System.getProperty("user.dir") + "/Backend/init/src/main/resources/image/user/";

        User user = new User();
        try {
            /*
             * 프로필 이미지 저장 순번-파일명으로 저장
             */
            if(thumbnail == null) { // 프로필 사진 입력 안함
                //프로필 이미지 저장 (기본 이미지)
                String filePath = BASE_PATH + "default.png";
                user.setUserthumbnail(getShortFilePath(filePath));
            } else { // 프로필 사진 입력함
                String filePath = BASE_PATH + userRegisterInfo.getUserEmail() + "_" + thumbnail.getOriginalFilename();
                File dest = new File(filePath);
                thumbnail.transferTo(dest);
                if (!dest.exists()) { // 파일 존재 x, 이 부분이 현재 null 값이 들어갈 수 있음, 추후 다시 해야됨
                    System.out.println("파일 업로드 실패");
                } else {
                    user.setUserthumbnail(getShortFilePath(filePath));
                }
            }
            user.setUserEmail(userRegisterInfo.getUserEmail());
            user.setUseraccount(userRegisterInfo.getUseraccount());
            // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
            user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
            user.setUsernickname(userRegisterInfo.getUsernickname());
            user.setUseraccount(userRegisterInfo.getUseraccount());
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
        String BASE_PATH = System.getProperty("user.dir") + "/Backend/init/src/main/resources/image/user/";
        String pic_place = System.getProperty("user.dir") + "/Backend/init/src/main/resources/" + user.getUserthumbnail();
        try {
            if(thumbnail == null) { // 프로필 사진 입력 안함
                //프로필 이미지 저장 (기본 이미지)
                if (!pic_place.substring(pic_place.length()-11, pic_place.length()).equals("default.png")){
                    File before = new File(pic_place);
                    before.delete();
                }
                String filePath = BASE_PATH + "default.png";
                user.setUserthumbnail(getShortFilePath(filePath));
            } else { // 프로필 사진 입력함
                if (!pic_place.substring(pic_place.length()-11, pic_place.length()).equals("default.png")){
                    File before = new File(pic_place);
                    before.delete();
                }
                String filePath = BASE_PATH + user.getUserEmail() + "-" + thumbnail.getOriginalFilename();
                File dest = new File(filePath);
                thumbnail.transferTo(dest);
                if (!dest.exists()) { // 파일 존재 x, 이 부분이 현재 null 값이 들어갈 수 있음, 추후 다시 해야됨
                    System.out.println("파일 업로드 실패");
                } else {
                    user.setUserthumbnail(getShortFilePath(filePath));
                }
            }
            user.setUserEmail(user.getUserEmail());
            if (!putinfo.getPassword().equals("")){
                user.setPassword(passwordEncoder.encode(putinfo.getPassword()));
            }
            user.setUsernickname(putinfo.getUsernickname());
            user.setUseraccount(user.getUseraccount());
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

    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    private String getShortFilePath(String path) {
        int idx = path.indexOf("image");
        return path.substring(idx, path.length());
    }

}
