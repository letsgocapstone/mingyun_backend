package org.example.spring_jwt.article.service;

import lombok.RequiredArgsConstructor;
import org.example.spring_jwt.article.Exception.ArticleNotFoundException;
import org.example.spring_jwt.article.dto.ArticleRequest;
import org.example.spring_jwt.article.dto.ArticleResponse;
import org.example.spring_jwt.article.entity.ArticleEntity;
import org.example.spring_jwt.article.repository.ArticleRepository;
import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.repository.UserRepository;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.example.spring_jwt.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class ArticleService {
//
//    private final ArticleRepository articleRepository;
//    private final UserRepository userRepository;
//    private final PlaceRepository placeRepository;
//
//    public ArticleEntity getArticleById(int id) {
//        return articleRepository.findById(id)
//                .orElseThrow(() -> new ArticleNotFoundException("게시글을 찾을 수 없습니다. ID: " + id));
//    }
//    @Transactional
//    public ArticleEntity createArticle(ArticleRequest request, Integer userId, Integer placeId) {
//        // 작성자 (UserEntity)와 장소 (PlaceEntity)를 찾음
//        UserEntity user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));
//        PlaceEntity place = placeRepository.findById(placeId)
//                .orElseThrow(() -> new IllegalArgumentException("Place not found with id " + placeId));
//
//        // DTO를 Entity로 변환
//        ArticleEntity article = new ArticleEntity();
//        article.setTitle(request.getTitle());
//        article.setContent(request.getContent());
//        article.setCreateTime(request.getCreateTime());
//        article.setUser(user);
//        article.setPlace(place);
//
//
//        // 저장
//        return articleRepository.save(article);
//    }
//}

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // 게시글 생성
    @Transactional
    public void createArticle(ArticleRequest request) {
        ArticleEntity article = new ArticleEntity();
        article.setId(request.getId()); // 이미 ArticleEntity와 ArticleRequest 구조가 맞다고 가정
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setUser(article.getUser());  // 예시 필드
        article.setPlace(article.getPlace());
        articleRepository.save(article);
    }

    // 게시글 조회

    public ArticleResponse getArticleResponseById(int id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("게시글을 찾을 수 없습니다. ID: " + id));

        PlaceEntity place = article.getPlace();
        UserEntity user = article.getUser();

        return ArticleResponse.fromEntity(article, place, user);
    }
}
