package com.blockback.init.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class User_Club_Join extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid")
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clubid")
    Club club;

    String position;
}
