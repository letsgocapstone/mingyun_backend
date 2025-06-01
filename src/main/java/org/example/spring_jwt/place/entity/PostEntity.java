package org.example.spring_jwt.place.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.spring_jwt.entity.UserEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(length = 2000) // 500자 이상 보장
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

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;

}
