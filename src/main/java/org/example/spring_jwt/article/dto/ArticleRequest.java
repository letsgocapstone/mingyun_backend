package org.example.spring_jwt.article.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.spring_jwt.article.entity.ArticleEntity;
import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.entity.PlaceEntity;

import java.time.LocalDateTime;

//RequestDTO	클라이언트 → 서버	게시글 작성
@Getter
@Setter
public class ArticleRequest {

    private int id;
    private String title;
    private String content;
    private LocalDateTime createTime;

    //from PlaceEntity
    private Double latitude;
    private Double longitude;
    private String placeImageURL;
    private Integer rating;

    //from UserEntity
    private String username;

    public static ArticleRequest fromEntity(ArticleEntity article, PlaceEntity place, UserEntity user ) {
        ArticleRequest request = new ArticleRequest();
        request.setId(article.getId());
        request.setTitle(article.getTitle());
        request.setContent(article.getContent());
        request.setCreateTime(article.getCreateTime());

        //from PlaceEntity
        request.setLongitude(place.getLongitude());
        request.setLatitude(place.getLatitude());
        request.setPlaceImageURL(place.getPlaceImageURL());
        request.setRating(place.getRating());

        //from UserEntity
        request.setUsername(user.getUsername());


        return request;
    }
}
