package com.blockback.init.common.jwt;

import com.blockback.init.entity.User;
import com.blockback.init.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SsafyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserEmail(username);
        if(user != null) {
            com.blockback.init.common.jwt.SsafyUserDetails userDetails = new com.blockback.init.common.jwt.SsafyUserDetails(user);
            return userDetails;
        }
        return null;
    }
}
