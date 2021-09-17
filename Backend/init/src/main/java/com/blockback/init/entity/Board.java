package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    // 즉시로딩, 지연 로딩, 게시글과 user 즉시 로딩이 좋을듯
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    User user;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "clubid")
//    Club club;

    String title;
    String content;
    Long view;
    LocalDateTime created;
    LocalDateTime updated;
    String section;

}
