package org.example.spring_jwt.place.service;

import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.dto.LoadPlaceDTO;
import org.example.spring_jwt.place.dto.PlaceDTO;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.example.spring_jwt.place.entity.PostEntity;
import org.example.spring_jwt.place.entity.TagEntity;
import org.example.spring_jwt.place.repository.PlaceRepository;
import org.example.spring_jwt.place.repository.PostRepository;
import org.example.spring_jwt.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class PlaceService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PostRepository postRepository;

    public PlaceService(UserRepository userRepository, PlaceRepository placeRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.postRepository = postRepository;
    }

    public PlaceEntity addPlaceProcess(PlaceDTO placeDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByUsername(username);

        PlaceEntity place = new PlaceEntity();
        place.setPlaceTitle(placeDTO.getPlaceTitle());
//        place.setPlaceDescription(placeDTO.getPlaceDescription());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());
        place.setPlaceImageURL(placeDTO.getPlaceImageURL());
//        place.setRating(placeDTO.getRating());
        place.setUser(user);

//        placeRepository.save(place);
        return placeRepository.save(place); // 저장 후 엔티티 반환

    }

    public LoadPlaceDTO findNearbyLocations(double lat, double lng) {
        double radius = 10.0; // km 단위

        List<PlaceEntity> entities = placeRepository.findNearbyPlaces(lat, lng, radius);

        List<PlaceDTO> dtos = entities.stream().map(this::convertToDTO).toList();
        System.out.println(dtos.getFirst().getTags());

        LoadPlaceDTO response = new LoadPlaceDTO();
        response.setPoi(dtos);
        return response;
    }
    private PlaceDTO convertToDTO(PlaceEntity entity) {
        PlaceDTO dto = new PlaceDTO();
        PostEntity post = postRepository.findFirstByPlaceId(entity.getId());
        if (post != null && post.getTags() != null) {
            List<String> tagNames = post.getTags().stream()
                    .map(TagEntity::getName)
                    .toList();
            dto.setTags(tagNames);
        }


        dto.setPlaceTitle(entity.getPlaceTitle());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setPlaceId(entity.getId());
        dto.setPlaceImageURL(entity.getPlaceImageURL());
//        String imagePath = entity.getPlaceImageURL(); // ex: "/path/to/image.jpg"
//        try {
//            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
//            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//            dto.setPlaceImageURL(base64Image); // Base64 문자열로 저장
////            System.out.println(dto.getPlaceImageURL());
//        } catch (Exception e) {
//            dto.setPlaceImageURL(null); // 실패 시 null로 처리
//            e.printStackTrace(); // 필요에 따라 로깅
//        }
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        return dto;
    }
}
