package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Club extends BaseEntity {

    String name;

    String introduce;

    String profile_thumbnail;

    String clubaccount;

    String password = null;

    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    User user;
}
