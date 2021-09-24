package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Schedule extends BaseEntity {

    String title;
    String content;

    @Temporal(TemporalType.TIMESTAMP)
    Date start;

    @Temporal(TemporalType.TIMESTAMP)
    Date end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    Club club;
}
