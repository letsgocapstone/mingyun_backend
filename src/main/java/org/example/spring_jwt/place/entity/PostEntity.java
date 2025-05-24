package org.example.spring_jwt.place.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.spring_jwt.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private Integer rating;

    @CreationTimestamp
    private LocalDateTime createTime;


    @ManyToOne // 여러 PlaceDomain이 하나의 UserEntity와 연관될 수 있음
    @JoinColumn(name = "userid")    // 외래 키로 사용될 컬럼 이름
    private UserEntity user;

    @ManyToOne // 여러 PlaceDomain이 하나의 UserEntity와 연관될 수 있음
    @JoinColumn(name = "placeid")    // 외래 키로 사용될 컬럼 이름
    private PlaceEntity place;

}
