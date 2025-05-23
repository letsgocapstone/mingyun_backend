package org.example.spring_jwt.repository;

import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
    int findIdByUsername(String username);

}
