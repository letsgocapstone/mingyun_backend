package org.example.spring_jwt.article.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_jwt.article.dto.ArticleRequest;
import org.example.spring_jwt.article.entity.ArticleEntity;
import org.example.spring_jwt.article.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleEntity> createArticle(@RequestBody ArticleRequest articleRequest,
                                                       @RequestParam Integer userId,
                                                       @RequestParam Integer placeId) {
        ArticleEntity article = articleService.createArticle(articleRequest, userId, placeId);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleEntity> getArticleById(@PathVariable int articleId) {
        ArticleEntity article = articleService.getArticleById(articleId);
        return ResponseEntity.ok(article);  // 200 OK 응답
    }
}
