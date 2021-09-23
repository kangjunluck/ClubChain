package com.blockback.init.repository;

import com.blockback.init.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long userId);

    Optional<User> findByUserEmail(String userEmail);

}

