package com.blockback.init.service;

import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;
import com.blockback.init.repository.ClubRepository;
import com.blockback.init.repository.UserClubRepository;
import com.blockback.init.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service("userclubService")
public class UserClubServiceImpl implements UserClubService {

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserClubRepository userClubRepository;

    @Override
    public void signClub(User user, Long clubid) {
        User_Club_Join userclub = new User_Club_Join();
        userclub.setUser(user);

        Optional<Club> club = clubRepository.findById(clubid);
        userclub.setClub(club.get());
        userclub.setPosition("정회원");
        userClubRepository.save(userclub);

    }

    @Override
    public User_Club_Join getUserClubByUserIdandClubId(Long userid, Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        Optional<User> user = userRepository.findById(userid);
        Optional<User_Club_Join> userclub = userClubRepository.findByUserAndClub(user.get(), club.get());
        if (userclub.isPresent()) {
            return userclub.get();
        }
        return null;
    }

    @Override
    public void resignClub(User user, Long clubid) {
        Optional<Club> club = clubRepository.findById(clubid);
        Optional<User_Club_Join> userclub = userClubRepository.findByUserAndClub(user, club.get());
        userClubRepository.delete(userclub.get());
    }
}
