package org.example.spring_jwt.place.repository;

import org.example.spring_jwt.place.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);
    List<TagEntity> findTop10ByNameStartingWithIgnoreCase(String prefix);

}
