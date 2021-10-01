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
public class File extends BaseEntity {

    String origin_file;
    String save_file;
    String save_folder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    Board board;
}
