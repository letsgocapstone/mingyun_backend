package org.example.spring_jwt.place.controller;

import org.example.spring_jwt.entity.UserEntity;
import org.example.spring_jwt.place.dto.PlaceDTO;
import org.example.spring_jwt.place.dto.PostDTO;
import org.example.spring_jwt.place.entity.PlaceEntity;
import org.example.spring_jwt.place.service.PlaceService;
import org.example.spring_jwt.place.service.PostService;
import org.example.spring_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/place")
//@RequiredArgsConstructor
public class AddPlaceController {

    private final PlaceService placeService;
    private final UserRepository userRepository;
    private final PostService postService;

    public AddPlaceController(PlaceService placeService, UserRepository userRepository, PostService postService) {
        this.placeService = placeService;
        this.userRepository = userRepository;
        this.postService = postService;
    }
    // 장소 등록 API
    @Value("${spring.my.path}")
    private String basePath;
    @PostMapping("/add")
    public ResponseEntity<String> addPlace(
            @RequestParam("placeTitle") String placeTitle,
            @RequestParam("lat") Double latitude,
            @RequestParam("lng") Double longitude,
            @RequestParam("rating") Integer rating,
            @RequestParam("tags") List<String> tags,
            @RequestParam("placeDescription") String placeDescription,
            @RequestParam("placeImageURL") MultipartFile imageFile) throws IOException {

        PlaceDTO placeDTO = new PlaceDTO();
        PostDTO postDTO = new PostDTO();



//        String path = "/Users/mingyun/Desktop/project/spring_jwt/src/main/java/org/example/spring_jwt/place/image/"+imageFile.getOriginalFilename();
        String path = basePath+imageFile.getOriginalFilename();
        imageFile.transferTo(new File(path));

        placeDTO.setPlaceTitle(placeTitle);
        placeDTO.setLatitude(latitude);
        placeDTO.setLongitude(longitude);
        placeDTO.setPlaceImageURL(path);

        PlaceEntity savedPlace = placeService.addPlaceProcess(placeDTO);

//        placeDTO.setRating(rating);
//        placeDTO.setPlaceDescription(placeDescription);
        postDTO.setTitle(placeTitle);
        postDTO.setContent(placeDescription);
        postDTO.setRating(rating);
        postDTO.setPlaceId(savedPlace.getId());

//        System.out.println("Adding place " + placeDTO.getPlaceImageURL());


        postService.addPost(postDTO, savedPlace, tags);
        return ResponseEntity.ok("장소가 성공적으로 등록되었습니다.");
    }
}
