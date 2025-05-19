package org.example.spring_jwt.article.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.spring_jwt.article.entity.ArticleEntity;

@Getter
@Setter
public class ArticleResponse {

    private int id;
    private String title;
    private String content;

    public static ArticleResponse fromEntity(ArticleEntity article) {
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        return response;
    }
}
