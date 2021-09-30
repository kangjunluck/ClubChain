package com.blockback.init.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    // response 시, 나타나지 않도록 하기 위해
    // 쓸때만 접근이 가능하다
    String userEmail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String usernickname;

    String userthumbnail;

    String useraccount;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", username='" + usernickname + '\'' +
                ", profile_thumbnail='" + userthumbnail + '\'' +
                ", account='" + useraccount + '\'' +
                '}';
    }
}
