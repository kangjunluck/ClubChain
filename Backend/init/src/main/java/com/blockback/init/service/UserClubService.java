package com.blockback.init.service;

import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;

public interface UserClubService {
    void signClub(User user, Long clubid);
    User_Club_Join getUserClubByUserIdandClubId(Long userid, Long clubid);
}
