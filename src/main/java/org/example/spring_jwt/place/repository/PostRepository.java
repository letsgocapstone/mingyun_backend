package org.example.spring_jwt.place.repository;

import org.example.spring_jwt.place.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    PostEntity findFirstByPlaceId(Integer placeId);
}
