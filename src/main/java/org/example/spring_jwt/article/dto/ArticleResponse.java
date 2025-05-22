package org.example.spring_jwt.article.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.spring_jwt.article.entity.ArticleEntity;
import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.entity.PlaceEntity;

import java.time.LocalDateTime;


//ResponseDTO	서버 → 클라이언트 / 게시글 조회, 사용자 정보 반환
@Getter
@Setter
public class ArticleResponse {

    private int id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private String tag;

    //from PlaceEntity
    private Double latitude;
    private Double longitude;
    private String placeImageURL;
    private Integer rating;
    private Integer placeId;


    //from UserEntity
    private String username;

    public static ArticleResponse fromEntity(ArticleEntity article, PlaceEntity place, UserEntity user ) {
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setCreateTime(article.getCreateTime());
        response.setTag(article.getTag());
        //from PlaceEntity
        response.setLongitude(place.getLongitude());
        response.setLatitude(place.getLatitude());
        response.setPlaceImageURL(place.getPlaceImageURL());
        response.setRating(place.getRating());

        //from UserEntity
        response.setUsername(user.getUsername());


        return response;
    }
}
