package com.blockback.init.repository;

import com.blockback.init.entity.Club;
import com.blockback.init.entity.User;
import com.blockback.init.entity.User_Club_Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserClubRepository extends JpaRepository<User_Club_Join, Long> {

    Optional<User_Club_Join> findByUserAndClub(User user, Club club);
}
