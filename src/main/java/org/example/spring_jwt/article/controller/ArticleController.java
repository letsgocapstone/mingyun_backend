package org.example.spring_jwt.article.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_jwt.article.dto.ArticleRequest;
import org.example.spring_jwt.article.dto.ArticleResponse;
import org.example.spring_jwt.article.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 생성
    @PostMapping("/add")
    public ResponseEntity<String> createArticle(@RequestBody ArticleRequest request) {
        articleService.createArticle(request);
        return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
    }

    // 게시글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable int id) {
        ArticleResponse response = articleService.getArticleResponseById(id);
        return ResponseEntity.ok(response);
    }
}
