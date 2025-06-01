package org.example.spring_jwt.place.controller;

import org.example.spring_jwt.place.dto.LoadPlaceDTO;
import org.example.spring_jwt.place.dto.PlaceDTO;
import org.example.spring_jwt.place.entity.TagEntity;
import org.example.spring_jwt.place.repository.TagRepository;
import org.example.spring_jwt.place.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoadPlaceController {
    private final PlaceService placeService;
    private final TagRepository tagRepository;

    public LoadPlaceController(PlaceService placeService, TagRepository tagRepository) {
        this.placeService = placeService;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/load_location")
    public ResponseEntity<LoadPlaceDTO> loadLocation(@RequestParam double lat, @RequestParam double lng) {
        System.out.println("Loading location");
        System.out.println("latitude: " + lat);
        System.out.println("longitude: " + lng);

        LoadPlaceDTO response = placeService.findNearbyLocations(lat, lng);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tags/autocomplete")
    public ResponseEntity<List<String>> autocompleteTags(@RequestParam("prefix") String prefix) {
        System.out.println("Autocompleting tags");
        List<TagEntity> tags = tagRepository.findTop10ByNameStartingWithIgnoreCase(prefix);
        System.out.println(tags);
        List<String> tagNames = tags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toList());
        System.out.println(tagNames);
        return ResponseEntity.ok(tagNames);
    }
}
