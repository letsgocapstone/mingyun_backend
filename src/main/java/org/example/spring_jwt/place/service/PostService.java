package org.example.spring_jwt.place.service;

import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.dto.PostDTO;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.example.spring_jwt.place.entity.PostEntity;
import org.example.spring_jwt.place.repository.PlaceRepository;
import org.example.spring_jwt.place.repository.PostRepository;
import org.example.spring_jwt.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
//        this.placeRepository = placeRepository;
    }

    public void addPost(PostDTO postDTO, PlaceEntity placeEntity) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username);

        PostEntity post = new PostEntity();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setRating(postDTO.getRating());
        post.setCreateTime(LocalDateTime.now());
        post.setUser(user);
        post.setPlace(placeEntity); // 이미 조회된 엔티티 사용

        postRepository.save(post);
    }

    public PostDTO findPostByPlaceId(int placeId) {
        PostEntity postEntity = postRepository.findFirstByPlaceId(placeId);
        if (postEntity == null) {
            return null;
        }

        PostDTO dto = convertToDTO(postEntity);

        // 작성자 userId로 UserEntity 조회 후 username 가져오기
        int userId = postEntity.getUser().getId();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("작성자 정보를 찾을 수 없습니다."));

        dto.setUserId(userId);
        dto.setUsername(user.getUsername());  // PostDTO에 username 필드 추가 필요

        // 장소 이미지 URL 세팅
        if (postEntity.getPlace() != null) {
            dto.setPlaceImageURL(postEntity.getPlace().getPlaceImageURL());
        }
        PlaceEntity place = postEntity.getPlace();
        if (place != null) {
            dto.setPlaceId(place.getId());
            dto.setPlaceImageURL(place.getPlaceImageURL());

            // null safe
            dto.setLatitude(place.getLatitude() != null ? place.getLatitude() : 0.0);
            dto.setLongitude(place.getLongitude() != null ? place.getLongitude() : 0.0);
        }


        return dto;
    }


    private PostDTO convertToDTO(PostEntity entity) {
        PostDTO dto = new PostDTO();
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setRating(entity.getRating());
        dto.setCreateTime(entity.getCreateTime());
        dto.setPlaceId(entity.getPlace() != null ? entity.getPlace().getId() : 0);
        dto.setLongitude(entity.getPlace().getLongitude());
        dto.setLatitude(entity.getPlace().getLatitude());
        // userId와 username은 별도 처리 (예: 서비스에서 userRepository 통해 조회)
        return dto;
    }




}
