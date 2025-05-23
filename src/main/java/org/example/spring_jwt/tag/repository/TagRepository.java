package org.example.spring_jwt.tag.repository;

import org.example.spring_jwt.tag.Entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);
    List<TagEntity> findByNameContaining(String keyword); // 키워드 포함 검색 (부분 일치)
}

