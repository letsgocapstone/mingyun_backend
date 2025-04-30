package org.example.spring_jwt.place.controller;

import org.example.spring_jwt.place.dto.LoadPlaceDTO;
import org.example.spring_jwt.place.dto.PlaceDTO;
import org.example.spring_jwt.place.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoadPlaceController {
    private final PlaceService placeService;

    public LoadPlaceController(PlaceService placeService) {
        this.placeService = placeService;
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
}
