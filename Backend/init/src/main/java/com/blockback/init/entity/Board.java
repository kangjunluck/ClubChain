package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Board extends BaseEntity {

    // 즉시로딩, 지연 로딩, 게시글과 user 즉시 로딩이 좋을듯
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clubid")
    Club club;

    String title;
    String content;
    int view;
    @Temporal(TemporalType.TIMESTAMP)
    Date created;
    @Temporal(TemporalType.TIMESTAMP)
    Date updated;
    String section;

    @Override
    public String toString() {
        return "Board{" +
                "user=" + user +
                ", club=" + club +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", view=" + view +
                ", created=" + created +
                ", updated=" + updated +
                ", section='" + section + '\'' +
                '}';
    }
}
