package org.example.spring_jwt.place.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private String title;
    private Integer rating;
    private String content;
    private LocalDateTime createTime;
    private int userId;
    private String username;       // 작성자 이름 추가
    private int placeId;
    private String placeImageURL;  // 장소 이미지 URL 추가
    private Double latitude;
    private Double longitude;
    private List<String> tags;
}
