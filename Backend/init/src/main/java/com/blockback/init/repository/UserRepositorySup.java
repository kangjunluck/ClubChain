package com.blockback.init.repository;

import com.blockback.init.entity.QUser;
import com.blockback.init.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositorySup {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QUser qUser = QUser.user;


//    QUser_Room qUser_room = QUser_Room.user_Room;
//    QRoom qRoom = QRoom.room;

    public Optional<User> findUserByUserEmail(String userEmail) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userEmail.eq(userEmail)).fetchOne();
        if(user == null) return Optional.ofNullable(new User());
        return Optional.ofNullable(user);
    }

//    public Optional<User_Room> findUserRoomByNickName(String nickname) {
//        User_Room user = jpaQueryFactory.select(qUser_room).from(qUser_room)
//                .where(qUser_room.username.eq(nickname)).fetchOne();
//
//        if(user == null) return Optional.ofNullable(new User_Room());
//        return Optional.ofNullable(user);
//    }
//
//    public Optional<Room> findRoomByRoomName(String roomname)
//    {
//        Room room = jpaQueryFactory.select(qRoom).from(qRoom)
//                .where(qRoom.roomname.eq(roomname)).fetchOne();
//
//        if(room == null) return Optional.ofNullable(new Room());
//        return Optional.ofNullable(room);
//
//    }
}
