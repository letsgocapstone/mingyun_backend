package org.example.spring_jwt.place.controller;

import org.example.spring_jwt.place.dto.PostDTO;
import org.example.spring_jwt.place.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class LoadPostController {
    private final PostService postService;

    public LoadPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/place_id={placeId}")
    public ResponseEntity<PostDTO> getPostByPlaceId(@PathVariable int placeId) {
        PostDTO post = postService.findPostByPlaceId(placeId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(post);
        return ResponseEntity.ok(post);
    }
}
