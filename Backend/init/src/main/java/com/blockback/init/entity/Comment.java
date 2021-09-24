package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity {

    String content;

    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardid")
    Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    User user;
}
