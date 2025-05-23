package org.example.spring_jwt.article.repository;
import org.example.spring_jwt.article.entity.ArticleEntity;
import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
}

