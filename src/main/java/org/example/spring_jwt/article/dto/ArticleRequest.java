package org.example.spring_jwt.article.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleRequest {

    private String title;
    private String content;
    private LocalDateTime createTime;

    // 생성자, toEntity() 등 필요한 메서드를 추가할 수 있습니다.
}
